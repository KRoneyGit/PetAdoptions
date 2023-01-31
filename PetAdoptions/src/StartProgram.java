
/**
* @author Kevin Roney - karoney
* CIS 175 - Fall 2023
* Jan 30, 2023
*/
import java.util.List;
import java.util.Scanner;

import controller.PetHelper;
import model.Pet;

public class StartProgram {

	static Scanner in = new Scanner(System.in);
	static PetHelper ph = new PetHelper();
	
	private static void addPet() {
		System.out.print("Enter name of pet: ");
		String name = in.nextLine();
		System.out.print("Enter species of pet: ");
		String species = in.nextLine();
		System.out.print("Enter age of pet: ");
		int age = in.nextInt();
		in.nextLine();
		
		Pet newPet = new Pet(name, species, age);
		ph.insertPet(newPet);
	}
	
	private static void deletePet() {
		System.out.print("Enter name of pet to delete: ");
		String name = in.nextLine();
		System.out.print("Enter species of pet to delete: ");
		String species = in.nextLine();
		System.out.print("Enter age of pet to delete: ");
		int age = in.nextInt();
		in.nextLine();

		Pet toDelete = new Pet(name, species, age);
		ph.deletePet(toDelete);
	}
	
	private static void editPet() {
		System.out.println("How would you like to search?");
		System.out.println("1 : Search by Name");
		System.out.println("2 : Search by Species");
		System.out.println("3 : Search by Age");
		int searchBy = in.nextInt();
		in.nextLine();
		List<Pet> foundPets;
		if (searchBy == 1) {
			System.out.print("Enter the pet name: ");
			String petName = in.nextLine();
			foundPets = ph.searchForPetByName(petName);
		} else if (searchBy == 2) {
			System.out.print("Enter the pet species: ");
			String petSpecies = in.nextLine();
			foundPets = ph.searchForPetBySpecies(petSpecies);
		} else {
			System.out.print("Enter the pet age: ");
			int petAge = in.nextInt();
			in.nextLine();
			foundPets = ph.searchForPetByAge(petAge);
		}
		
		if (!foundPets.isEmpty()) {
			System.out.println("Found Results.");
			for(Pet p : foundPets) {
				System.out.println(p.getId() + " : " + p.returnPetInfo());
			}
			System.out.print("Which ID to edit: ");
			int idToEdit = in.nextInt();
			in.nextLine();
			
			Pet toEdit = ph.searchForPetById(idToEdit);
			System.out.println("\nRetrieved " + toEdit.returnPetInfo());
			System.out.println("1 : Update Name");
			System.out.println("2 : Update Species");
			System.out.println("3 : Update Age");
			int update = in.nextInt();
			in.nextLine();
			
			if (update == 1) {
				System.out.print("New Name: ");
				String newName = in.nextLine();
				toEdit.setName(newName);
			} else if(update == 2) {
				System.out.print("New Species: ");
				String newSpecies = in.nextLine();
				toEdit.setSpecies(newSpecies);
			} else {
				System.out.print("New Age: ");
				int newAge = in.nextInt();
				in.nextLine();
				toEdit.setAge(newAge);
			}
			
			ph.updateItem(toEdit);
			
		} else {
			System.out.println("NO RESULTS FOUND");
		}
	}
	
	public static void main(String[] args) {
		runMenu();
	}
	
	public static void runMenu() {
		boolean goAgain = true;
		System.out.println("Furry friends waiting for a new home!");
		while (goAgain) {
			System.out.println("\nWhat would you like to do?");
			System.out.println("| 1 | Add a pet");
			System.out.println("| 2 | Edit a pet's info");
			System.out.println("| 3 | Delete a pet's info");
			System.out.println("| 4 | View all pets");
			System.out.println("| 5 | Close program");
			int selection = in.nextInt();
			in.nextLine();
			
			if (selection == 1) {
				addPet();
			} else if (selection == 2) {
				editPet();
			} else if (selection == 3) {
				deletePet();
			} else if (selection == 4) {
				viewAllPets();
			} else {
				ph.cleanUp();
				System.out.println("Goodbye");
				goAgain = false;
			}
		}
	}
	
	private static void viewAllPets() {
		List<Pet> allPets = ph.showAllPets();
		for(Pet pet : allPets) {
			System.out.println(pet.returnPetInfo());
		}
	}
}
