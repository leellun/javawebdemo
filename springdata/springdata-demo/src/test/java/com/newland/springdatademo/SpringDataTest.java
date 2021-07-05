package com.newland.springdatademo;

import com.newland.springdatademo.methodtest.AddressRepository;
import com.newland.springdatademo.po.Person;
import com.newland.springdatademo.test1.PersonRepository;
import com.newland.springdatademo.test1.PersonService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import javax.sql.DataSource;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
@SpringBootTest
public class SpringDataTest {

	private ApplicationContext ctx = null;
	private PersonRepository personRepository = null;
	private PersonService personService;

	{
		ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		personRepository = ctx.getBean(PersonRepository.class);
		personService = ctx.getBean(PersonService.class);
	}

	@Test
	public void testCommonCustomRepositoryMethod(){
		ApplicationContext ctx2 = new ClassPathXmlApplicationContext("classpath:com/atguigu/springdata/commonrepositorymethod/applicationContext2.xml");
		AddressRepository addressRepository = ctx2.getBean(AddressRepository.class);
		addressRepository.method();
	}

	@Test
	public void testCustomRepositoryMethod(){
		personRepository.test2();
	}

	/**
	 * Ŀ��: ʵ�ִ���ѯ�����ķ�ҳ. id > 5 ������
	 *
	 * ���� JpaSpecificationExecutor �� Page<T> findAll(Specification<T> spec, Pageable pageable);
	 * Specification: ��װ�� JPA Criteria ��ѯ�Ĳ�ѯ����
	 * Pageable: ��װ�������ҳ����Ϣ: ���� pageNo, pageSize, Sort
	 */
	@Test
	public void testJpaSpecificationExecutor(){
		int pageNo = 3 - 1;
		int pageSize = 5;
		PageRequest pageable = new PageRequest(pageNo, pageSize);

		//ͨ��ʹ�� Specification �������ڲ���
		Specification<Person> specification = new Specification<Person>() {
			/**
			 * @param *root: �����ѯ��ʵ����.
			 * @param query: ���Դ��пɵ� Root ����, ����֪ JPA Criteria ��ѯҪ��ѯ��һ��ʵ����. ������
			 * ����Ӳ�ѯ����, �����Խ�� EntityManager ����õ����ղ�ѯ�� TypedQuery ����.
			 * @param *cb: CriteriaBuilder ����. ���ڴ��� Criteria ��ض���Ĺ���. ��Ȼ���Դ��л�ȡ�� Predicate ����
			 * @return: *Predicate ����, ����һ����ѯ����.
			 */
			@Override
			public Predicate toPredicate(Root<Person> root,
										 CriteriaQuery<?> query, CriteriaBuilder cb) {
				Path path = root.get("id");
				Predicate predicate = cb.gt(path, 5);
				return predicate;
			}
		};

		Page<Person> page = personRepository.findAll(specification, pageable);

		System.out.println("�ܼ�¼��: " + page.getTotalElements());
		System.out.println("��ǰ�ڼ�ҳ: " + (page.getNumber() + 1));
		System.out.println("��ҳ��: " + page.getTotalPages());
		System.out.println("��ǰҳ��� List: " + page.getContent());
		System.out.println("��ǰҳ��ļ�¼��: " + page.getNumberOfElements());
	}

	@Test
	public void testJpaRepository(){
		Person person = new Person();
		person.setBirth(new Date());
		person.setEmail("xy@atguigu.com");
		person.setLastName("xyz");
		person.setId(28);

		Person person2 = personRepository.saveAndFlush(person);

		System.out.println(person == person2);
	}

	@Test
	public void testPagingAndSortingRespository(){
		//pageNo �� 0 ��ʼ.
		int pageNo = 6 - 1;
		int pageSize = 5;
		//Pageable �ӿ�ͨ��ʹ�õ��� PageRequest ʵ����. ���з�װ����Ҫ��ҳ����Ϣ
		//������ص�. Sort ��װ���������Ϣ
		//Order �Ǿ��������ĳһ�����Խ��������ǽ���.
		Order order1 = new Order(Direction.DESC, "id");
		Order order2 = new Order(Direction.ASC, "email");
		Sort sort = new Sort(order1, order2);

		PageRequest pageable = new PageRequest(pageNo, pageSize, sort);
		Page<Person> page = personRepository.findAll(pageable);

		System.out.println("�ܼ�¼��: " + page.getTotalElements());
		System.out.println("��ǰ�ڼ�ҳ: " + (page.getNumber() + 1));
		System.out.println("��ҳ��: " + page.getTotalPages());
		System.out.println("��ǰҳ��� List: " + page.getContent());
		System.out.println("��ǰҳ��ļ�¼��: " + page.getNumberOfElements());
	}

	@Test
	public void testCrudReposiory(){
		List<Person> persons = new ArrayList<>();

		for(int i = 'a'; i <= 'z'; i++){
			Person person = new Person();
			person.setAddressId(i + 1);
			person.setBirth(new Date());
			person.setEmail((char)i + "" + (char)i + "@atguigu.com");
			person.setLastName((char)i + "" + (char)i);

			persons.add(person);
		}

		personService.savePersons(persons);
	}

	@Test
	public void testModifying(){
//		personRepository.updatePersonEmail(1, "mmmm@atguigu.com");
		personService.updatePersonEmail("mmmm@atguigu.com", 1);
	}

	@Test
	public void testNativeQuery(){
		long count = personRepository.getTotalCount();
		System.out.println(count);
	}

	@Test
	public void testQueryAnnotationLikeParam(){
//		List<Person> persons = personRepository.testQueryAnnotationLikeParam("%A%", "%bb%");
//		System.out.println(persons.size());

//		List<Person> persons = personRepository.testQueryAnnotationLikeParam("A", "bb");
//		System.out.println(persons.size());

		List<Person> persons = personRepository.testQueryAnnotationLikeParam2("bb", "A");
		System.out.println(persons.size());
	}

	@Test
	public void testQueryAnnotationParams2(){
		List<Person> persons = personRepository.testQueryAnnotationParams2("aa@atguigu.com", "AA");
		System.out.println(persons);
	}

	@Test
	public void testQueryAnnotationParams1(){
		List<Person> persons = personRepository.testQueryAnnotationParams1("AA", "aa@atguigu.com");
		System.out.println(persons);
	}

	@Test
	public void testQueryAnnotation(){
		Person person = personRepository.getMaxIdPerson();
		System.out.println(person);
	}

	@Test
	public void testKeyWords2(){
		List<Person> persons = personRepository.getByAddress_IdGreaterThan(1);
		System.out.println(persons);
	}

	@Test
	public void testKeyWords(){
		List<Person> persons = personRepository.getByLastNameStartingWithAndIdLessThan("X", 10);
		System.out.println(persons);

		persons = personRepository.getByLastNameEndingWithAndIdLessThan("X", 10);
		System.out.println(persons);

		persons = personRepository.getByEmailInAndBirthLessThan(Arrays.asList("AA@atguigu.com", "FF@atguigu.com",
				"SS@atguigu.com"), new Date());
		System.out.println(persons.size());
	}

	@Test
	public void testHelloWorldSpringData() throws FileNotFoundException, IOException, InstantiationException, IllegalAccessException{
		System.out.println(personRepository.getClass().getName());

		Person person = personRepository.getByLastName("AA");
		System.out.println(person);
	}

	@Test
	public void testJpa(){

	}

	@Test
	public void testDataSource() throws SQLException {
		DataSource dataSource = ctx.getBean(DataSource.class);
		System.out.println(dataSource.getConnection());
	}

}
