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
//            // 객체를 저장한 상태 (영속)
//            em.persist(member1);

            //팀 저장
            Team team = new Team();
            team.setName("TeamA");
            em.persist(team);

            //회원 저장
            Member member = new Member();
            member.setName("member1");
            member.setTeam(team); //단방향 연관관계 설정, 참조 저장
            em.persist(member);

            em.flush();
            em.clear();

            //조회
            Member findMember = em.find(Member.class, member.getId());

            //참조를 사용해서 연관관계 조회
            Team findTeam = findMember.getTeam();

            System.out.println(findTeam.getName());


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
