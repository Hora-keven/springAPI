package br.com.erudio.services;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.erudio.exceptions.ResourceNotFoundException;
import br.com.erudio.model.Person;
import br.com.erudio.repositories.PersonRepository;

@Service
public class PersonServices {

	private Logger logger = Logger.getLogger(PersonServices.class.getName());
	
	@Autowired
	PersonRepository repository;
	
	public List<Person> findAll() {
		List<Person> persons = new ArrayList<>();
		
		repository.findAll();
		return repository.findAll();;
	
	
	}

	public Person findById(Long id) {
		logger.info("Finding one person!");
		
		return repository.findById(id).orElseThrow(()-> new ResourceNotFoundException("No records found for this ID"));
	}
	public Person create(Person person) {
		logger.info("creating person!");
		return repository.save(person);
	}
	public Person update(Person person) {
		logger.info("updating person!");
		
		var entity = repository.findById(person.getId()).orElseThrow(()-> new ResourceNotFoundException("No records found for this ID"));
		entity.setAddress(person.getAddress());
		entity.setFirstName(person.getFirstName());
		entity.setLastName(person.getLastName());
		entity.setGender(person.getGender());
		
		return repository.save(person);
	}
	public void delete(Long id) {
		logger.info("Deleting person!");
		var entity = repository.findById(id).orElseThrow(()-> new ResourceNotFoundException("No records found for this ID"));
		repository.delete(entity);
		
	}
	
	
}
