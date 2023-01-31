package controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import model.Pet;

/**
* @author Kevin Roney - karoney
* CIS 175 - Fall 2023
* Jan 30, 2023
*/
public class PetHelper {
	static EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("PetAdoptions");

	/**
	 * @param newPet
	 */
	public void insertPet(Pet newPet) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		em.persist(newPet);
		em.getTransaction().commit();
		em.close();
	}

	public List<Pet> showAllPets(){
		EntityManager em = emfactory.createEntityManager();
		List<Pet> allPets = em.createQuery("SELECT p FROM Pet p").getResultList();
		return allPets;
	}
	
	public void deletePet(Pet toDelete) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<Pet> typedQuery = em.createQuery("select p from Pet p where p.name = :selectedName and p.species = :selectedSpecies and p.age = :selectedAge",Pet.class);
		
		typedQuery.setParameter("selectedName", toDelete.getName());
		typedQuery.setParameter("selectedSpecies", toDelete.getSpecies());
		typedQuery.setParameter("selectedAge", toDelete.getAge());
		
		typedQuery.setMaxResults(1);
		
		Pet result = typedQuery.getSingleResult();
		
		em.remove(result);
		em.getTransaction().commit();
		em.close();
	}
	
	public Pet searchForPetById(int id) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		Pet found = em.find(Pet.class, id);
		em.close();
		return found;
	}
	
	public List<Pet> searchForPetByName(String name){
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<Pet> typedQuery = em.createQuery("select p from Pet p where p.name = :selectedName", Pet.class);
		typedQuery.setParameter("selectedName", name);
		
		List<Pet> foundPets = typedQuery.getResultList();
		em.close();
		return foundPets;
	}
	
	public List<Pet> searchForPetBySpecies(String species){
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<Pet> typedQuery = em.createQuery("select p from Pet p where p.species = :selectedSpecies", Pet.class);
		typedQuery.setParameter("selectedSpecies", species);
		
		List<Pet> foundPets = typedQuery.getResultList();
		em.close();
		return foundPets;
	}
	
	public List<Pet> searchForPetByAge(int age){
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<Pet> typedQuery = em.createQuery("select p from Pet p where p.age = :selectedAge", Pet.class);
		typedQuery.setParameter("selectedAge", age);
		
		List<Pet> foundPets = typedQuery.getResultList();
		em.close();
		return foundPets;
	}
	
	public void updateItem(Pet toUpdate) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		em.merge(toUpdate);
		em.getTransaction().commit();
		em.close();
	}
	
	public void cleanUp() {
		emfactory.close();
	}
}
