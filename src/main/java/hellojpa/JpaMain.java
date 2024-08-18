package hellojpa;

import jakarta.persistence.*;

public class JpaMain {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
//            //1. 객체 저장
//            // 객체 생성 상태 (비영속)
//            Member member1 = new Member(150L, "A");
//            Member member2 = new Member(160L, "B");
//
//            // 객체를 저장한 상태 (영속)
//            em.persist(member1);
//            em.persist(member2);

            //2. 객체 변경
            Member member = em.find(Member.class, 150L);
            member.setName("ZZZZZ");

            tx.commit();
        } catch (Exception e) {
            tx.rollback(); // 오류 발생 시 트랜잭션 롤백
            e.printStackTrace();
        } finally {
            em.close(); // EntityManager 닫기
        }

        emf.close(); // EntityManagerFactory 닫기
    }
}
