package org.example;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.junit.jupiter.api.Test;

public class ChangeMachine {

  /*
   * This method should:
   * Return the fewest number of bills possible to make change for the amount passed in given the denominations you can make change with.
   *
   * amount: The amount of money passed in to make change for.
   * denominations: The bills you can use to make change.  IE: 1s, 5s, 10s, 20s, etc.
   *
   * Other info:
   * You will always be given denominations that will be able to make even change (no remainders).
   * Only positive amounts and positive denominations will be passed in.
   * Neither the amount nor the denominations will ever be null.
   * The amount will always be greater than 0.
   */
  public Map<Integer, Integer> makeChange(Integer amount, Set<Integer> denominations) {

    Map<Integer, Integer> results = new HashMap<>();

    // Sort the denominations so the biggest denomination is first 
    // Ex: [50, 20, 10, 1]
    List<Integer> orderedDenominations = new ArrayList<>(denominations);
    Collections.sort(orderedDenominations, Collections.reverseOrder());

    Integer remainingAmount = Integer.valueOf(amount);

    // Loop through each of our denomination values, largest first
    for(Integer denomination: orderedDenominations ){

      // If we are able to deduct the denomination value from our remaining amount, do it until we can't
      while(remainingAmount - denomination >= 0){

        remainingAmount = remainingAmount - denomination;

        // First time seeing this denomination
        if(results.get(denomination) == null) {
          results.put(denomination, Integer.valueOf(1));
        }

        // Increment the number for this denomination  
        else{
          results.put(denomination, results.get(denomination) + 1);
        }
      }
      
      // Don't look at all denominations if we don't need to 
      if(remainingAmount == 0){
        break;
      }
      
    }

    return results;
  }

  @Test
  public void test() {
    ChangeMachine changeMachine = new ChangeMachine();

    //change for $55 should be 2:20s, 1:10, and 1:5
    Map<Integer, Integer> result = changeMachine.makeChange(55, new HashSet<>(Arrays.asList(5,10,1,20)));
    assertEquals(Integer.valueOf(2), result.get(20));
    assertEquals(Integer.valueOf(1), result.get(10));
    assertEquals(Integer.valueOf(1), result.get(5));

    //change for $33 should be 6:5s and 3:1s
    result = changeMachine.makeChange(33, new HashSet<>(Arrays.asList(1,5)));
    assertEquals(Integer.valueOf(6), result.get(5));
    assertEquals(Integer.valueOf(3), result.get(1));

    //change for $104 should be 2:50s and 4:1s  
    result = changeMachine.makeChange(104, new HashSet<>(Arrays.asList(1,10,5,50,20)));
    assertEquals(Integer.valueOf(2), result.get(50));
    assertEquals(Integer.valueOf(4), result.get(1));
  }

}