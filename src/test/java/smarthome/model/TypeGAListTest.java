package smarthome.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class TypeGAListTest {

    @Test
    void newTypeGA() {
        TypeGAList tga = new TypeGAList ();

        TypeGA village= tga.newTypeGA ("village");

        assertEquals("village",village.getTypeGA ());
    }

    @Test
    void addTypeGA() {
        TypeGAList tga = new TypeGAList ();
        TypeGA city= tga.newTypeGA ("city");

        tga.addTypeGA (city);
        List<TypeGA> expectedResult = Arrays.asList (city);
        List<TypeGA> result = tga.getTypeGAList ();

        assertEquals (expectedResult, result);
    }

    @DisplayName("set type of GA for village")
    @Test
    public void defineTypesOfGeographicalAreaVillage() {
        TypeGAList tga = new TypeGAList ();
        TypeGA village= tga.newTypeGA ("village");

        assertEquals (0, tga.getTypeGAList ().size ());
        tga.addTypeGA (village);
        assertEquals (1, tga.getTypeGAList ().size ());

        List<TypeGA> expectedResult = Arrays.asList (village);
        List<TypeGA> result = tga.getTypeGAList ();

        assertEquals (expectedResult, result);
    }

    @DisplayName("set Already Contained type of GA for village")
    @Test
    public void defineAlreadyContainedTypeOfGeograficalAreaVillage() {
        TypeGAList tga = new TypeGAList ();
        TypeGA village1= tga.newTypeGA ("village");
        TypeGA village2= tga.newTypeGA ("village");

        assertEquals (0, tga.getTypeGAList ().size ());
        tga.addTypeGA (village1);
        assertEquals (1, tga.getTypeGAList ().size ());
        tga.addTypeGA (village2);
        assertEquals (1, tga.getTypeGAList ().size ());

        List<TypeGA> expectedResult = Arrays.asList (village1);
        List<TypeGA> result = tga.getTypeGAList ();

        assertEquals (expectedResult, result);
    }

    @DisplayName("empty type of GA")
    @Test
    public void nameEmpty() {
        TypeGAList tga = new TypeGAList ();
        TypeGA village1= tga.newTypeGA (" ");

        assertEquals (0, tga.getTypeGAList ().size ());
        tga.addTypeGA (village1);
        assertEquals (0, tga.getTypeGAList ().size ());

        List<TypeGA> expectedResult = Arrays.asList ();
        List<TypeGA> result = tga.getTypeGAList ();

        assertEquals (expectedResult, result);
    }

    @DisplayName("null type of GA")
    @Test
    public void nameNull() {
        TypeGAList tga = new TypeGAList ();
        TypeGA village1= tga.newTypeGA (null);
        TypeGA village2= tga.newTypeGA ("city");
        TypeGA village3= tga.newTypeGA ("country");

        assertEquals (0, tga.getTypeGAList ().size ());
        tga.addTypeGA (village1);
        assertEquals (0, tga.getTypeGAList ().size ());
        tga.addTypeGA (village2);
        assertEquals (1, tga.getTypeGAList ().size ());
        tga.addTypeGA (village3);
        assertEquals (2, tga.getTypeGAList ().size ());

        List<TypeGA> expectedResult = Arrays.asList (village2, village3);
        List<TypeGA> result = tga.getTypeGAList ();

        assertEquals (expectedResult, result);
    }

}