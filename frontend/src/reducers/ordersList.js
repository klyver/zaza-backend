import {combineReducers} from 'redux';

const FETCH_ORDERS = 'ordersList/FETCH_ORDERS';
const FETCH_ORDERS_SUCCESS = 'ordersList/FETCH_ORDERS_SUCCESS';
const FETCH_ORDERS_FAIL = 'ordersList/FETCH_ORDERS_FAIL';

const CONFIRM_ORDER = 'ordersList/CONFIRM_ORDER';
const CONFIRM_ORDER_SUCCESS = 'ordersList/CONFIRM_ORDER_SUCCESS';
const CONFIRM_ORDER_FAIL = 'ordersList/CONFIRM_ORDER_FAIL';

const CREATE_TEST_ORDER = 'ordersList/CREATE_TEST_ORDER';
const CREATE_TEST_ORDER_SUCCESS = 'ordersList/CREATE_TEST_ORDER_SUCCESS';
const CREATE_TEST_ORDER_FAIL = 'ordersList/CREATE_TEST_ORDER_FAIL';

// Reducer
const loading = (state = false, action) => {
    switch (action.type) {
        case FETCH_ORDERS:
            return true;
        case FETCH_ORDERS_SUCCESS:
            return false;
        case FETCH_ORDERS_FAIL:
            return false;
        case CONFIRM_ORDER:
            return true;
        case CONFIRM_ORDER_SUCCESS:
            return false;
        case CONFIRM_ORDER_FAIL:
            return false;
        case CREATE_TEST_ORDER:
            return true;
        case CREATE_TEST_ORDER_SUCCESS:
            return false;
        case CREATE_TEST_ORDER_FAIL:
            return false;
        default:
            return state;
    }
};
const orders = (state = [], action) => {
    switch (action.type) {
        case FETCH_ORDERS_SUCCESS:
            return action.result.data;
        case CONFIRM_ORDER_SUCCESS:
            return [];
        case CREATE_TEST_ORDER_SUCCESS:
            return [];
        default:
            return state;
    }
};

export default combineReducers({loading, orders});

// Actions
export const fetchOrderList = () => ({
    types: [FETCH_ORDERS, FETCH_ORDERS_SUCCESS, FETCH_ORDERS_FAIL],
    promise: client => client.get('/api/orderItems')
});

export const confirmOrderItemAction = (orderItemId) => ({
    types: [CONFIRM_ORDER, CONFIRM_ORDER_SUCCESS, CONFIRM_ORDER_FAIL],
    promise: client => client.post(`/api/orderItems/${orderItemId}/confirm`),
    afterSuccess: (dispatch, getState, response) => {
        console.log('afterSuccess');
        dispatch(fetchOrderList());
    }
});

export const createTestOrderAction = () => ({
    types: [CREATE_TEST_ORDER, CREATE_TEST_ORDER_SUCCESS, CREATE_TEST_ORDER_FAIL],
    promise: client => client.post('/api/order/createtestorder'),
    afterSuccess: (dispatch, getState, response) => {
        console.log('afterSuccess createTestOrder');
        dispatch(fetchOrderList());
    }
});


