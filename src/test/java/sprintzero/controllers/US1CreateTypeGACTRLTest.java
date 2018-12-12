package sprintzero.controllers;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import sprintzero.model.TypeGAList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class US1CreateTypeGACTRLTest {

    /**
     * Add a new type of geographical area in an empty list and get the size of the list that is 1.
     */
    @Test
    @DisplayName("Add a new type of geographical area in an empty list")
    void newTypeGAIfSuccessVillage() {
        TypeGAList list = new TypeGAList ();
        US1CreateTypeGACTRL ctrl1 = new US1CreateTypeGACTRL (list);

        assertEquals (0, list.getGAList ().size ());
        ctrl1.newTypeGA ("village");

        assertEquals (1, list.getGAList ().size ());
    }

    /**
     * Add a new type of geographical area in a list with other different types and get the size of the list that is 2.
     */
    @Test
    @DisplayName("Add a new type of geographical area in a list with other types")
    void newTypeGAIfSuccessCityAfterVillage() {
        TypeGAList list = new TypeGAList ();
        US1CreateTypeGACTRL ctrl1 = new US1CreateTypeGACTRL (list);

        assertEquals (0, list.getGAList ().size ());

        ctrl1.newTypeGA ("village");
        assertEquals (1, list.getGAList ().size ());

        ctrl1.newTypeGA ("city");
        assertEquals (2, list.getGAList ().size ());
    }


    /**
     * Add a new type of geographical area in a list with the same type and get the size of the list that remains as 1.
     */
    @Test
    @DisplayName("Add a new type of geographical area in a list with same type")
    void newTypeGAIfCityAfterCity() {
        TypeGAList list = new TypeGAList ();
        US1CreateTypeGACTRL ctrl1 = new US1CreateTypeGACTRL (list);

        assertEquals (0, list.getGAList ().size ());

        ctrl1.newTypeGA ("city");
        assertEquals (1, list.getGAList ().size ());

        ctrl1.newTypeGA ("city");
        assertEquals (1, list.getGAList ().size ());
    }

    /**
     * Add new types of geographical area in a list with same and different types and get the size of the list.
     * The repetitions are not considered.
     */
    @Test
    @DisplayName("Add  new types of geographical area in a list with same and different types")
    void newTypeGAIfRepeatAndDifferentTypes() {
        TypeGAList list = new TypeGAList ();
        US1CreateTypeGACTRL ctrl1 = new US1CreateTypeGACTRL (list);

        assertEquals (0, list.getGAList ().size ());

        ctrl1.newTypeGA ("city");
        assertEquals (1, list.getGAList ().size ());

        ctrl1.newTypeGA ("village");
        assertEquals (2, list.getGAList ().size ());

        ctrl1.newTypeGA ("city");
        assertEquals (2, list.getGAList ().size ());

        ctrl1.newTypeGA ("country");
        assertEquals (3, list.getGAList ().size ());

        ctrl1.newTypeGA ("country");
        assertEquals (3, list.getGAList ().size ());
    }

    /**
     * Add spaces has type of geographical area in an empty list and confirm that is not possible to add that spaces
     * into the list
     */
    @Test
    @DisplayName("Add a wrong type of geographical area in an empty list")
    void newTypeGAIfSpaceNotAddedEmptyList() {
        TypeGAList list = new TypeGAList ();
        US1CreateTypeGACTRL ctrl1 = new US1CreateTypeGACTRL (list);

        assertEquals (0, list.getGAList ().size ());
        ctrl1.newTypeGA (" ");

        assertEquals (0, list.getGAList ().size ());
    }

    /**
     * Add spaces has type of geographical area in an list with one TypeGA and confirm that is not possible to add that
     * space into the list but is possible to add a correct one after
     */
    @Test
    @DisplayName("Add a wrong type of geographical area in a list")
    void newTypeGAIfSpaceNotAdded() {
        TypeGAList list = new TypeGAList ();
        US1CreateTypeGACTRL ctrl1 = new US1CreateTypeGACTRL (list);

        assertEquals (0, list.getGAList ().size ());

        ctrl1.newTypeGA ("village");
        assertEquals (1, list.getGAList ().size ());

        ctrl1.newTypeGA ("            ");
        assertEquals (1, list.getGAList ().size ());

        ctrl1.newTypeGA ("city");
        assertEquals (2, list.getGAList ().size ());
    }


}