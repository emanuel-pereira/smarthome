import React from "react";
import ReactDOM from "react-dom";
import {createBrowserHistory} from "history";
import {Router, Route, Switch, Redirect} from "react-router-dom";
import configureStore from './store/configureStore';
import {Provider} from 'react-redux';
import {LoginPage} from 'LoginPage';
import decode from 'jwt-decode';

import "bootstrap/dist/css/bootstrap.css";
import "assets/scss/paper-dashboard.scss?v=1.1.0";
import "assets/demo/demo.css";
import "perfect-scrollbar/css/perfect-scrollbar.css";

import AdminLayout from "layouts/Admin.jsx";
import RegularLayout from "layouts/Regular.jsx";

const hist = createBrowserHistory();
const store = configureStore();


if (localStorage.getItem('token') == null) {
    ReactDOM.render(
        <Router history={hist}>
            <Provider store={store}>
                <div>
                    <LoginPage/>
                </div>
            </Provider>
        </Router>,
        document.getElementById("root")
    );
}
else if (decode(localStorage.getItem('token')).sub === "admin") {
    ReactDOM.render(
        <Router history={hist}>
            <Provider store={store}>
                <Switch>
                    <Route path="/admin" render={props => <AdminLayout {...props} />}/>
                    <Redirect to="/admin/Home"/>
                </Switch>
            </Provider>
        </Router>,
        document.getElementById("root")
    );
}
else if (decode(localStorage.getItem('token')).sub === "regular") {
    ReactDOM.render(
        <Router history={hist}>
            <Provider store={store}>
                <Switch>
                    <Route path="/regular" render={props => <RegularLayout {...props} />}/>
                    <Redirect to="/regular/Home"/>
                </Switch>
            </Provider>
        </Router>,
        document.getElementById("root")
    );
}