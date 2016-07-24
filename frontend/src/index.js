import ReactDOM from 'react-dom';
import React from 'react';
import {Provider} from 'react-redux';
import {bindActionCreators} from 'redux';
import configureStore from 'config/store';
import {setupAxiosInterceptors} from 'rest/axios';
import {redirectToLoginWithMessage, logout} from 'reducers/authentication';
import {syncHistoryWithStore} from 'react-router-redux'

import {Router, Route, IndexRoute, browserHistory} from 'react-router';
import App from './ui/container/App';
import HomePage from './ui/container/HomePage';
import UsersPage from './ui/container/UsersPage';
import ProductOptionsPage from './ui/container/ProductOptionsPage';
import LoginPage from './ui/container/LoginPage';
import ProductsPage from './ui/container/ProductsPage';
import OrdersPage from './ui/container/OrdersPage';
import ProductEditPage from './ui/container/ProductEditPage';
import ProductCreateNewPage from './ui/container/ProductCreateNewPage';
import privateRoute from './router/privateRoute';

const store = configureStore();
const history = syncHistoryWithStore(browserHistory, store);

const actions = bindActionCreators({redirectToLoginWithMessage, logout}, store.dispatch);
setupAxiosInterceptors(() => actions.redirectToLoginWithMessage('LOGIN_ERROR_UNAUTHORIZED'));

ReactDOM.render(
    <Provider store={store}>
        <div>
            <Router history={history}>
                <Route path="/" name="app" component={App}>
                    <IndexRoute component={HomePage}/>
                    <Route path="products" component={ProductsPage}/>
                    <Route path="products/new" component={ProductCreateNewPage}/>
                    <Route path="products/:productId" component={ProductEditPage}/>
                    <Route path="orders" component={OrdersPage}/>
                    <Route path="users" component={privateRoute('ADMIN', UsersPage)}/>
                    <Route path="productoptions" component={privateRoute('ADMIN', ProductOptionsPage)}/>
                    <Route path="login" component={LoginPage}/>
                    <Route path="logout" onEnter={actions.logout}/>
                </Route>
            </Router>
        </div>
    </Provider>,
    document.getElementById('root')
);
