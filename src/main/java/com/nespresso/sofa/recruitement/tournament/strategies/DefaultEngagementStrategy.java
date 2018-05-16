package com.nespresso.sofa.recruitement.tournament.strategies;

import com.nespresso.sofa.recruitement.tournament.equipment.Equipment;
import com.nespresso.sofa.recruitement.tournament.fighter.Fighter;
import com.nespresso.sofa.recruitement.tournament.weapon.Weapon;

public final class DefaultEngagementStrategy implements EngagementStrategy
{
  
  private Fighter<?> firstFighter;
  
  private Fighter<?> secondFighter;

  @Override
  public void setFirstFighter(Fighter<?> fighter)
  {
    firstFighter = fighter;
  }

  @Override
  public void setSecondFighter(Fighter<?> fighter)
  {
    secondFighter = fighter;
  }
  
  private int reduceAttackerDamage(int damage, final Fighter<?> attacker)
  {
    for (final Equipment equipment : attacker.getEquipments())
    {
      damage = equipment.reduceOwnDamage(damage);
      
      if (damage <= 0)
      {
        return 0;
      }
    }
    
    return damage;
  }
  
  private int reduceDamage(int damage, final Class<? extends Weapon> weaponType, final Fighter<?> defender)
  {
    if (damage <= 0)
    {
      return 0;
    }
    
    for (final Equipment equipment : defender.getEquipments())
    {
      damage = equipment.reduceDamage(damage, weaponType);
      
      if (damage <= 0)
      {
        return 0;
      }
    }
    
    return damage;
  }

  private void attack(final Fighter<?> attacker, final Fighter<?> defender)
  {
    defender.setHitPoints(Math.max(0, defender.hitPoints() - reduceDamage(reduceAttackerDamage(attacker.getWeapon().getDamage(), attacker), attacker.getWeapon().getClass(), defender)));
  }
  
  @Override
  public void engage()
  {
    while(firstFighter.hitPoints() > 0 && secondFighter.hitPoints() > 0)
    {
      attack(firstFighter, secondFighter);
      
      if (secondFighter.hitPoints() > 0)
      {
        attack(secondFighter, firstFighter);
      }
    }
  }

}
