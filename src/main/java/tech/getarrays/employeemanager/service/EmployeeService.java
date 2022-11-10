package tech.getarrays.employeemanager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.getarrays.employeemanager.exception.EmployeeNotFoundException;
import tech.getarrays.employeemanager.model.Employee;
import tech.getarrays.employeemanager.repository.EmployeeRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class EmployeeService {
    private final EmployeeRepository employeeRepo;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepo) {
        this.employeeRepo = employeeRepo;
    }

    public Employee addEmployee(Employee employee) {
        return employeeRepo.save(employee);
    }

    public List<Employee> findAllEmployees() {
        return employeeRepo.findAll();
    }

    public Employee findEmployeeById(Long id) {
        return employeeRepo.findEmployeeById(id)
                .orElseThrow(() -> new EmployeeNotFoundException(id));
    }

    public void deleteEmployee(Long id){
        Optional<Employee> deletedEmployee = employeeRepo.findEmployeeById(id);
        if(deletedEmployee.isEmpty()){
            throw new EmployeeNotFoundException(id);
        } else {
            employeeRepo.deleteEmployeeById(id);
        }
    }

    public Optional<Employee> updateEmployee(Long id, Employee newEmployee) {
        Optional<Employee> updatedEmployee = employeeRepo.findEmployeeById(id);
        if (updatedEmployee.isEmpty()){
            throw new EmployeeNotFoundException(id);
        } else {
            return updatedEmployee.map(employee -> {
                employee.setName(newEmployee.getName());
                employee.setPhone(newEmployee.getPhone());
                employee.setEmail(newEmployee.getEmail());
                employee.setJobTitle(newEmployee.getJobTitle());
                employee.setImageUrl(newEmployee.getImageUrl());
                return employeeRepo.save(employee);
            });
        }
    }
}
