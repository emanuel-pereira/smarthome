package smarthome.controller;

import smarthome.model.*;

    public class GetDeviceListInGridByTypeCTRL {

        private House mHouse;

        public GetDeviceListInGridByTypeCTRL (House house) {
            mHouse = house;
        }

        public HouseGridList getHouseGridListCtrl() {
            return mHouse.getHGListInHouse();
        }

        public int getHGListSizeCtrl() {
            return mHouse.getHGListInHouse().getSize();
        }

        public String showHouseGridListInStringCtrl() {
            return this.getHouseGridListCtrl().showHouseGridListInString();
        }

        public HouseGrid getHouseGrid(int indexHG){
            return this.getHouseGridListCtrl().get(indexHG-1);
        }

        public String getHouseGridName(int indexHG){
            return this.getHouseGrid(indexHG).getName();
        }

        public RoomList getListOfRoomsInGrid(int indexHG) {
            return this.getHouseGrid(indexHG).getRoomListInAGrid();
        }

        public int getRoomListSizeCtrl(int indexHG) {
            return this.getListOfRoomsInGrid(indexHG).getRoomListSize();
        }

        public DeviceList getDeviceListInGridCtrl(int indexHG) {
            return this.getHouseGrid(indexHG).getDeviceListInGrid();
        }

        public int getDeviceListInGridSizeCtrl(int indexHG){
            return this.getDeviceListInGridCtrl(indexHG).size();
        }

        public DeviceList deviceListGroupByTypeCtrl(int indexHG) {
            return this.getHouseGrid(indexHG).getDeviceListInGridGroupBy();

        }

        public String showGroupedDeviceListInGridString (int indexHG) {
            return this.getHouseGrid(indexHG).showGroupedDeviceListInGridString();
        }
    }