import {combineReducers} from 'redux';
import categories from './categories';
import productOptionList from './productOptionList';
import productList from './productList';
import product from './product';
import ordersList from './ordersList';
import creatingProduct from './creatingProduct';
import updatingProduct from './updatingProduct';
import authentication from './authentication';
import {routerReducer as routing} from 'react-router-redux';

export default combineReducers({
    categories,
    productOptionList,
    productList,
    product,
    creatingProduct,
    updatingProduct,
    ordersList,
    authentication,
    routing
});
