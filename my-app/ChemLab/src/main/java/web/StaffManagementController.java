package web;

import java.util.logging.Logger;
import entity.*;
import function.*;
import web.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class StaffManagementController {

	@Autowired 
	private StaffManagementFunction function;
	private final Logger log = LoggerFactory.getLogger(StaffManagementController.class);
	
	public StaffManagementController(StaffManagementFunction function) {
		this.function = function;
	}
	
	@GetMapping("/staffs")
	Map<StaffManagement> allStaff() {
		return function.findAllStaff();
	}
	
	@GetMappering("staff/{id}")
	ResponseEntity<?> getStaff(@PathVariable Long id) {
		Optional <StaffManagement> staff = function.findStaffById(id);
		return staff.map(response -> ResponseEntity.ok().body(response))
				.orElse(new ReponseEntity<>(HttpStatus.NOT_FOUND));
	}
	
	@GetMappering("staff/{name}")
	ResponseEntity<?> getStaff(@PathVariable String name) {
		Optional <StaffManagement> staff = function.findStaffByName(name);
		return staff.map(response -> ResponseEntity.ok().body(response))
				.orElse(new ReponseEntity<>(HttpStatus.NOT_FOUND));
	}
	
	@PostMapping("/staff")
	ResponseEntity<StaffManagement> createStaff(@Valid @RequestBody StaffManagementArgumentObject staffAO) throws URISyntaxException {
		log.info("Request to create staff: {}", staffAO);
		StaffManagement result = function.addStaff(staffAO);
		return ResponseEntity.created(new URI("/api/staff/" + result.getId())).body(result);
	}
	
	@PutMapping("/staff/{id}")
	ResponseEntity<StaffManagement> editStaff(@PathVariable Long id, @Valid @RequestBody StaffManagementArgumentObject staffAO) {
		staffAO.setId(id);
		log.info("Request to update staff: {}", staffAO);
		StaffManagement result = function.updateStaff(staffAO);
		return ResponseEntity.ok().body(result);
	}
	
	@DeleteMapping("/staff/{id}")
	public ResponseEntity<?> deleteStaff(@PathVariable Long id) {
		log.info("Request to delete staff: {}", id);
		function.deleteById(id);
		return ResponseEntity.ok().build();
	}
}
