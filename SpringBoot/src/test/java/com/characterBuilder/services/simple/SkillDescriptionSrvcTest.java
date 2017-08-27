package com.characterBuilder.services.simple;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.characterBuilder.entities.SkillMap;
import com.characterBuilder.services.interfaces.SkillDescriptionSrvc;
import com.characterBuilder.throwable.exceptions.InputTooLong;
import com.characterBuilder.throwable.exceptions.OverwritingDataException;
import com.characterBuilder.util.PropertiesUtil;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SkillDescriptionSrvcTest {

	@Autowired
	private SkillDescriptionSrvc skillDescSrvc;
	
	private SkillMap skillMap;
	private String description;
	
	@Before
	public void initialize() {
		skillMap = new SkillMap();
		skillMap.setId(3);
		description = buildMaxDescription();
	}
	
	@Test
	public void testUpdate() {
	  try {
	  skillDescSrvc.update(skillMap, description);
	  assert(true);
	  } catch (OverwritingDataException | InputTooLong e) {
	    //This should not throw exceptions, exceptions come from the add method which
	    //is throughly tested in other methods.
	    assert(false);
	  }
	  checkDescription(skillMap, description);
	  
	  String newDesc = "Bar Hug";
	  try {
	    skillDescSrvc.update(skillMap, newDesc);
	    assert(true);
	  } catch (OverwritingDataException | InputTooLong e) {
	    //This should not throuw exceptions, exceptions come from the add method which
	    //is thourougly tested in other methods.
	    assert(false);
	  }
	  checkDescription(skillMap, newDesc);
	  testDelete(skillMap);
	}

	/**
	 * Varifies add works iff data does not exist in the database for the particular skill.
	 * 
	 * @param skillMap - skill that the description is to be connected to.
	 * @param description
	 */
	@Test
	public void testAdd() {
	  try {

	    skillDescSrvc.add(skillMap, description);
	    assert(true);
	  } catch (OverwritingDataException e) {
	  //There should be no data in the dataBase.
	    assert(false);
	  } catch (InputTooLong e) {
	  //Input should not be too long.
	    assert(false);
	  }
	  checkDescription(skillMap, description);

	  try {
	    skillDescSrvc.add(skillMap, description);
	    assert(false);
	  } catch (OverwritingDataException e) {
	  //Data should already exist in the dataBase.
	    assert(true);
	  } catch (InputTooLong e) {
	  //Input should not be too long.
	  assert(false);
	  }
	  checkDescription(skillMap, description);
	  testDelete(skillMap);
	}

	/**
	 * Checks to be sure an exception is thrown for and input that is one character too long.
	 * @param skillMap
	 */
	@Test
	public void tooLongTest(){
	  try {
	        skillDescSrvc.add(skillMap, description + '!');
	        assert(false);
	      } catch (OverwritingDataException e) {
	      //There should not be no data in the dataBase.
	        assert(false);
	      } catch (InputTooLong e) {
	      //Input should be too long.
	        assert(true);
	      }
	}

	/**
	 * Varifies that an exception is thrown if an attempt is made to add a description over an
	 * existing one.
	 */
	@Test
	public void overWritingDataTest(){
	  SkillMap skill = new SkillMap();
	  skill.setId(1);
	  try {
	    skillDescSrvc.add(skill, "Hello World");
	    assert(false);
	  } catch (OverwritingDataException e) {
	    //There should be data in the dataBase.
	    assert(true);
	  } catch (InputTooLong e) {
	    //Input should not be too long.
	    assert(false);
	  }
	}

	/**
	 * Varifies the delete function successfully removes data.
	 * 
	 * @param skill - skill that the description is to be connected to.
	 */
	private void testDelete(SkillMap skillMap) {	  
	  skillDescSrvc.delete(skillMap);
	  try {
	    skillDescSrvc.add(skillMap, "hello world");
	    assert(true);
	  } catch (OverwritingDataException e) {
	    assert(false);
	  } catch (InputTooLong e) {
	  assert(false);
	  }
	  skillDescSrvc.delete(skillMap);
	}
	
	/*
	 * This should build a string equal to the descriptionLength variable defined in 
	 * charBuild.properties. By simply changing that number and running this test you
	 * can confirm weather the program can handle the given value.
	 */
	private String buildMaxDescription(){
		int maxLength = PropertiesUtil.getDescriptionLength();
		String templateString = "The history of nuclear physics as a discipline distinct from atomic physics starts with the discovery of radioactivity by Henri Becquerel in 1896,[2] while investigating phosphorescence in uranium salts.[3] The discovery of the electron by J. J. Thomson[4] a year later was an indication that the atom had internal structure. At the beginning of the 20th century the accepted model of the atom was J. J. Thomson\'s \"plum pudding\" model in which the atom was a positively charged ball with smaller negatively charged electrons embedded inside it.\n\nIn the years that followed, radioactivity was extensively investigated, notably by Marie and Pierre Curie as well as by Ernest Rutherford and his collaborators. By the turn of the century physicists had also discovered three types of radiation e"
				+ "manating from atoms, which they named alpha, beta, and gamma radiation. Experiments by Otto Hahn in 1911 and by James Chadwick in 1914 discovered that the beta decay spectrum was continuous rather than discrete. That is, electrons were ejected from the atom with a continuous range of energies, rather than the discrete amounts of energy that were observed in gamma and alpha decays. This was a problem for nuclear physics at the time, because it seemed to indicate that energy was not conserved in these decays.\n\nThe 1903 Nobel Prize in Physics was awarded jointly to Becquerel for his discovery and to Marie and Pierre Curie for their subsequent research into radioactivity. Rutherford was awarded the Nobel Prize in Chemistry in 1908 for his \"investigations into the disintegration of the elements and"
				+ " the chemistry of radioactive substances\".\nI am adding this sentance.\nIn 1905 Albert Einstein formulated the idea of mass–energy equivalence. While the work on radioactivity by Becquerel and Marie Curie predates this, an explanation of the source of the energy of radioactivity would have to wait for the discovery that the nucleus itself was composed of smaller constituents, the nucleons.\n\nRutherford\'s team discovers the nucleus[edit]\nIn 1906 Ernest Rutherford published \"Retardation of the α Particle from Radium in passing through matter.\"[5] Hans Geiger expanded on this work in a communication to the Royal Society[6] with experiments he and Rutherford had done, passing alpha particles through air, aluminum foil and gold leaf. More work was published in 1909 by Geiger and Ernest Marsden,[7] and further greatly exp"
				+ "lectrons. \n\nThe Rutherford model worked quite well until studies of nuclear spin were carried out banded work was published in 1910 by Geiger.[8] In 1911–1912 Rutherford went before the Royal Society to explain the experiments and propound the new theory of the atomic nucleus as we now understand it.\n\nThe key experiment behind this announcement was performed in 1910 at the University of Manchester: Ernest Rutherford\'s team performed a remarkable experiment in which Geiger and Marsden under Rutherford\'s supervision fired alpha particles (helium nuclei) at a thin film of gold foil. The plum pudding model had predicted that the alpha particles should come out of the foil with their trajectories being at most slightly bent. But Rutherford instructed his team to look for something that shocked him to observe: a few particles were scattered through large angles, even completely backwards in some"
				+ " cases. He likened it to firing a bullet at tissue paper and having it bounce off. The discovery, with Rutherford\'s analysis of the data in 1911, led to the Rutherford model of the atom, in which the atom have a very small, very dense nucleus containing most of its mass, and consisting of heavy positively charged particles with embedded electrons in order to balance out the charge (since the neutron was unknown). As an example, in this model (which is not the modern one) nitrogen-14 consisted of a nucleus with 14 protons and 7 electrons (21 total particles) and the nucleus was surrounded by 7 more orbiting e... THIS FILE IS 8000 CHARACTERS LONG! DAM I NEED 20 MORE CHARS.";
		
		while(templateString.length() < maxLength){
			templateString += templateString;
		}
		
		return templateString.substring(0, maxLength);
	}
	
	private void checkDescription(SkillMap skillMap, String description) {
		skillDescSrvc.setDescription(skillMap);

		assert(skillMap.getDescription().equals(description));
	}
}
