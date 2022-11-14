package tech.getarrays.employeemanager.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import tech.getarrays.employeemanager.model.Employee;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class EmployeeRepositoryTest {

    @Autowired
    private EmployeeRepository underTest;

    @Test
    void checkSearchById(){
        Employee employee = new Employee(
                1L,
                "Ricardo Ruben",
                "ricardo@gmail.com",
                "developer",
                "123123123",
                "2312312.png"
        );

        underTest.save(employee);

        Optional<Employee> searchedEmployee = underTest.findEmployeeById(1L);

        assertThat(searchedEmployee.equals(employee));
    }




}
