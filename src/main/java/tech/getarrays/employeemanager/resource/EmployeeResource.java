package tech.getarrays.employeemanager.resource;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.getarrays.employeemanager.model.Employee;
import tech.getarrays.employeemanager.service.EmployeeService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/employee")
public class EmployeeResource {
    private final EmployeeService employeeService;

    public EmployeeResource(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/all")
    public List<Employee> getAllEmployees() {
        return employeeService.findAllEmployees();
    }

    @GetMapping("/{id}")
    public Employee getEmployeeById (@PathVariable("id") Long id) {
        return employeeService.findEmployeeById(id);
    }

    @PostMapping("/add")
    public Employee addEmployee(@RequestBody Employee employee) {
        return employeeService.addEmployee(employee);
    }

    @PutMapping("/update/{id}")
    public Optional<Employee> updateEmployee(@PathVariable Long id, @RequestBody Employee employee) {
        Employee updatedEmployee = employeeService.findEmployeeById(id);
        return Optional.ofNullable(employeeService.addEmployee(employee));
    }

    @DeleteMapping("/delete/{id}")
    public void deleteEmployee(@PathVariable("id") Long id) {
        Employee deletedEmployee = employeeService.findEmployeeById(id);
        if (deletedEmployee != null){
            employeeService.deleteEmployee(id);
        }
    }
}
