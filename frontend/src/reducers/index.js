import {combineReducers} from 'redux';
import categories from './categories';
import productOptions from './productOptions';
import products from './products';
import orders from './orders';
import authentication from './authentication';
import {routerReducer as routing} from 'react-router-redux';

export default combineReducers({
    categories,
    productOptions,
    products,
    orders,
    authentication,
    routing
});
