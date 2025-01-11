package hellojpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class JpaMain {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        tx.begin();

        try {
            // insert
            // 비영속
            Member member = new Member();
            member.setId(3L);
            member.setName("HelloD");

//            Member member1 = new Member(150L, "A");
//            Member member2 = new Member(160L, "B");
//
//            System.out.println("=== BEFORE ===");
            // 영속 - 1차 캐시, 쓰기 지연 SQL 저장소에 저장
            em.persist(member);
//            em.persist(member1);
//            em.persist(member2);
            // 준영속 상태로 만드려면
//            em.detach(member); // 특정 entity만 준영속 상태로
//            em.clear(); // 모든 영속성 컨텍스트를 초기화
//            em.close();

//            System.out.println("=== AFTER ===");

            // select
            // 영속
//            Member findMember = em.find(Member.class, 1L);
//            System.out.println("findMember.getId() = " + findMember.getId());
//            System.out.println("findMember.getName() = " + findMember.getName());

//            Member findMember1 = em.find(Member.class, 1L);
            // 조회를 1번만 함(1차 캐시)
//            Member findMember2 = em.find(Member.class, 1L);
//            System.out.println("findMember1.getName() = " + findMember1.getName());
//            System.out.println("findMember2.getName() = " + findMember2.getName());
//            System.out.println("result = " + (findMember1 == findMember2)); // true

            // jpql
//            List<Member> result = em.createQuery("select m from Member m", Member.class)
//                    .setFirstResult(1)
//                    .setMaxResults(10)
//                    .getResultList();
//            for (Member member : result) {
//                System.out.println("member.getName() = " + member.getName());
//            }

            // update
            // 변경 감지(Dirty Checking) - java collection 처럼 쓰면 끝.
//            findMember.setName("HelloC");

            // delete
//            em.remove(findMember);


            // flush - 쓰기 지연 SQL 저장소에 있는 쿼리를 DB에 보내줌
            // 1차 캐시에 있는 entity와 snapshot 비교
            // 변경이 있을 시 update 쿼리 생성

            // flush 호출되는 시기(기본값) - FlushModeType.AUTO(바꿀 일 없음..)
            // em.flush();
            // jpql 쿼리 실행할 때
            // commit할 때

            // flush는 영속성 컨텍스트를 비우지 않음
            // flush는 영속성 컨텍스트의 변경 내용을 DB에 동기화

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}
