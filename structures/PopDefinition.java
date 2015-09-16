/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package structures;

/**
 *
 * @author Mike Hollingshaus
 * @version 20160916
 * @Purpose This object represents a dimension for a population. Thus far, the
 * Dimensions are age and sex. Later, it will be extended to homeType (i.e,
 * household or group quarters) The intersection defines the smallest level of
 * the population;
 */
public class PopDefinition {

    private final int sex, age;

    /*
     For sex, must be 1(Male) or 2(Female)
     For age, must be an integer 0-100 (anyway 100+ is clumed in with 100)
     */
    public PopDefinition(int sex, int age) {
        validate(sex, age);
        this.sex = sex;
        this.age = age;
    }

    private void validate(int s, int a) {
        if (sex < 1 || sex > 2) {
            throw new RuntimeException("Sex must be 1(Male) or 2(Female");
        }
        if (age < 0 || age > 100) {
            throw new RuntimeException("Age must be in range [0,100]");
        }
    }

    public int getSex() {
        return sex;
    }

    public int getAge() {
        return age;
    }
    
    
    
    
    
    
    
}
