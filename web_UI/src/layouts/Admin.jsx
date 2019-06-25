import React from "react";
// javascript plugin used to create scrollbars on windows
import PerfectScrollbar from "perfect-scrollbar";
import { Route, Switch } from "react-router-dom";

import DemoNavbar from "../components/Navbars/DemoNavbar.jsx";
import Footer from "../components/Footer/Footer.jsx";
import Sidebar from "../components/Sidebar/Sidebar.jsx";
import Home from "../views/Home";
import HouseArea from "../views/HouseArea";
import Room from "../views/Room";
import HouseGrid from "../views/HouseGrid";
//import {routes} from "../actions/actionsLogin";

var routes = [
  {
    path: "/Home",
    name: "Home",
    icon: "nc-icon nc-bank",
    component: Home,
    layout: "/admin"
  },
  {
    path: "/geoarea",
    name: "House Area",
    icon: "nc-icon nc-pin-3",
    component: HouseArea,
    layout: "/admin"
  },
  {
    path: "/rooms",
    name: "Rooms",
    icon: "nc-icon nc-app",
    component: Room,
    layout: "/admin"
  },
  {
    path: "/housegrids",
    name: "Power Grids",
    icon: "nc-icon nc-layout-11",
    component: HouseGrid,
    layout: "/admin"
  }];

var ps;

class Dashboard extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      backgroundColor: "black",
      activeColor: "info"
    };
    this.mainPanel = React.createRef();
  }
  componentDidMount() {
    if (navigator.platform.indexOf("Win") > -1) {
      ps = new PerfectScrollbar(this.mainPanel.current);
      document.body.classList.toggle("perfect-scrollbar-on");
    }
  }
  componentWillUnmount() {
    if (navigator.platform.indexOf("Win") > -1) {
      ps.destroy();
      document.body.classList.toggle("perfect-scrollbar-on");
    }
  }
  componentDidUpdate(e) {
    if (e.history.action === "PUSH") {
      this.mainPanel.current.scrollTop = 0;
      document.scrollingElement.scrollTop = 0;
    }
  }
  handleActiveClick = color => {
    this.setState({ activeColor: color });
  };
  handleBgClick = color => {
    this.setState({ backgroundColor: color });
  };




  render() {
    return (
      <div className="wrapper">
        <Sidebar
          {...this.props}
          routes={routes}
          bgColor={this.state.backgroundColor}
          activeColor={this.state.activeColor}
        />
        <div className="main-panel" ref={this.mainPanel}>
          <DemoNavbar {...this.props} />
          <Switch>
            {routes.map((prop, key) => {
              return (
                <Route
                  path={prop.layout + prop.path}
                  component={prop.component}
                  key={key}
                />
              );
            })}
          </Switch>
          <Footer fluid />
        </div>

      </div>
    );
  }
}

export default Dashboard;
