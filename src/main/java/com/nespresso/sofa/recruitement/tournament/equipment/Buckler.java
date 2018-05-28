package com.nespresso.sofa.recruitement.tournament.equipment;

import java.util.function.ToIntFunction;

public final class Buckler extends Equipment
{
  
  private int damageCounter = 0;
  
  private int axeDamageCounter = 0;
  
  private boolean damageDealtByAxe = false;
  
  private boolean isEffective()
  {
    return axeDamageCounter < 3;
  }

  @Override
  ToIntFunction<Class<? extends Equipment>> damageIncreaseWhenDealingDamage()
  {
    return weaponType -> 0;
  }

  @Override
  ToIntFunction<Class<? extends Equipment>> damageDecreaseWhenReceivingDamage()
  {
    return weaponType ->
    {
      if (isEffective())
      {
        if (weaponType == Axe.class)
        {
          damageDealtByAxe = true;
        }
        
        if (damageCounter % 2 == 0)
        {
          return Integer.MAX_VALUE;
        }
      }
      
      return 0;
    };
  }

  @Override
  public Runnable postEngageHook()
  {
    return () ->
    {
      damageCounter++;
      
      if (damageDealtByAxe)
      {
        damageDealtByAxe = false;
        axeDamageCounter++;
      }
    };
  }

}
