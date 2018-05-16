package com.nespresso.sofa.recruitement.tournament;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

import com.nespresso.sofa.recruitement.tournament.fighter.Highlander;
import com.nespresso.sofa.recruitement.tournament.fighter.Swordsman;
import com.nespresso.sofa.recruitement.tournament.fighter.Viking;

public class TournamentTest
{
  @Test
  public void SwordsmanVsViking()
  {

    Swordsman swordsman = new Swordsman();

    Viking viking = new Viking();

    swordsman.engage(viking);
    assertThat(swordsman.hitPoints()).isEqualTo(0);
    assertThat(viking.hitPoints()).isEqualTo(35);

  }

  @Test
  public void SwordsmanWithBucklerVsVikingWithBuckler()
  {

    Swordsman swordsman = new Swordsman().equip("buckler");

    Viking viking = new Viking().equip("buckler");

    swordsman.engage(viking);
    assertThat(swordsman.hitPoints()).isEqualTo(0);
    assertThat(viking.hitPoints()).isEqualTo(70);

  }

  @Test
  public void ArmoredSwordsmanVsViking()
  {
    Highlander highlander = new Highlander();

    Swordsman swordsman = new Swordsman().equip("buckler")
        .equip("armor");

    swordsman.engage(highlander);

    assertThat(swordsman.hitPoints()).isEqualTo(0);
    assertThat(highlander.hitPoints()).isEqualTo(10);

  }

  @Test
  public void ViciousSwordsmanVsVeteranHighlander()
  {

    Swordsman swordsman = new Swordsman("Vicious").equip("axe")
        .equip("buckler")
        .equip("armor");

    Highlander highlander = new Highlander("Veteran");

    swordsman.engage(highlander);

    assertThat(swordsman.hitPoints()).isEqualTo(1);
    assertThat(highlander.hitPoints()).isEqualTo(0);

  }
}
