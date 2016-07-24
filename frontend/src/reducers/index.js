import {combineReducers} from 'redux';
import productOptionList from './productOptionList';
import productList from './productList';
import product from './product';
import ordersList from './ordersList';
import creatingProduct from './creatingProduct';
import updatingProduct from './updatingProduct';
import authentication from './authentication';
import {routerReducer as routing} from 'react-router-redux';

export default combineReducers({
    productOptionList,
    productList,
    product,
    creatingProduct,
    updatingProduct,
    ordersList,
    authentication,
    routing
});
