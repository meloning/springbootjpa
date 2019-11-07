package com.example.springbootjpa;

import org.hibernate.Session;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Component
public class JpaRunner implements ApplicationRunner {

    @PersistenceContext // JPA code를 이용하면서 spring 구현체를 캡슐화 하는 방향
    EntityManager entityManager;

    @Transactional
    @Override
    public void run(ApplicationArguments args) throws Exception {
//        testMapping();
//        testCasCade();
        testJPQL();
    }

    private void testMapping() {
        // Transient 상태: jpa가 모르는 상태
        Account account = new Account();
        account.setUsername("junsu4");
        account.setPassword("hibernate");

        Study study = new Study();
        study.setName("Spring Data Jpa");
//        study.setOwner(account);

//        account.getStudies().add(study); // 객체지향적으로 본다면 양방향으로 참조를 해야하기 때문에 해줘야하는게 맞음
//        study.setOwner(account);
        account.addStudy(study);

//        entityManager.persist(account);
        Session session = entityManager.unwrap(Session.class);
        session.save(account);
        session.save(study); // Persistent: JPA가 관리중인 상태(1차 캐시, Dirty checking, write Behind...)

        // 1차 캐시중이기에 다시 달라고 하면
        // DB에서 가져오지 않고, Persistent상태에서 관리중인 캐싱된 데이터를 불러줌
        Account junsu3 = session.load(Account.class, account.getId());
        junsu3.setUsername("junsu_temp"); // Dirty Checking (hibernate단에서 update를 해도
        System.out.println("-----------------------------------------------------");
        System.out.println(junsu3.getUsername());
    }

    private void testCasCade() {
        Post post = new Post();
        post.setTitile("Spring Data JPA 언제 보나...");

        Comment comment = new Comment();
        comment.setComment("빨리 봐");
        post.addComment(comment);

        Comment comment1 = new Comment();
        comment1.setComment("곧 보여드림");
        post.addComment(comment1);

        Session session = entityManager.unwrap(Session.class);
        session.save(post);

        // cascade remove -> post and comment 모두 삭제
//        Session session = entityManager.unwrap(Session.class);
//        Post post = session.get(Post.class, 1L);
//        session.delete(post);

        // fetch mode
        Post fetchExPost = session.get(Post.class, 4L);
        System.out.println("=====================================");
        System.out.println(fetchExPost.getTitile());

        post.getComments().forEach( c -> {
            System.out.println("---------------------------");
            System.out.println(c.getComment());
        });
    }

    private void testJPQL() {
        TypedQuery<Post> query = entityManager.createQuery("SELECT p FROM Post AS p", Post.class);
        List<Post> posts = query.getResultList();
        posts.forEach(System.out::println);
    }
}
