3.1 a) 

B_EmployeeService_UnitTest:

assertThat(found.getName()).isEqualTo(name);
assertThat(fromDb).isNull();
assertThat(doesEmployeeExist).isTrue();
assertThat(fromDb.getName()).isEqualTo("john");



b)

@AutoConfigureMockMvc
@AutoConfigureTestDatabase


c)

   @BeforeEach
    public void setUp() {

        //these expectations provide an alternative to the use of the repository
        Employee john = new Employee("john", "john@deti.com");
        john.setId(111L);

        Employee bob = new Employee("bob", "bob@deti.com");
        Employee alex = new Employee("alex", "alex@deti.com");

        List<Employee> allEmployees = Arrays.asList(john, bob, alex);

        Mockito.when(employeeRepository.findByName(john.getName())).thenReturn(john);
        Mockito.when(employeeRepository.findByName(alex.getName())).thenReturn(alex);
        Mockito.when(employeeRepository.findByName("wrong_name")).thenReturn(null);
        Mockito.when(employeeRepository.findById(john.getId())).thenReturn(Optional.of(john));
        Mockito.when(employeeRepository.findAll()).thenReturn(allEmployees);
        Mockito.when(employeeRepository.findById(-99L)).thenReturn(Optional.empty());
    }


d)

O @Mock vem do Mockito e é usado em testes unitários, este nao entra no contexto de SpringBoot, já o @MockBean vem do Spring Boot e é usado em testes de integração

e)
este ficheiro neste caso está configurado para usar testes num ambiente Spring Boot

f)

Enquanto os testes D e E usam o @SpringBootTest o C usa testes unitários focados na layer do Controller. Entre o D e o E a diferença é que no D usamos o MockMvc para simular chamadas e no E usamos o TestRestTemplate para fazer chamadas reais.
