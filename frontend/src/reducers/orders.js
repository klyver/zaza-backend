const FETCH_ORDERS = 'orders/FETCH_ORDERS';
const FETCH_ORDERS_SUCCESS = 'orders/FETCH_ORDERS_SUCCESS';
const FETCH_ORDERS_FAIL = 'orders/FETCH_ORDERS_FAIL';

const CONFIRM_ORDER = 'orders/CONFIRM_ORDER';
const CONFIRM_ORDER_SUCCESS = 'orders/CONFIRM_ORDER_SUCCESS';
const CONFIRM_ORDER_FAIL = 'orders/CONFIRM_ORDER_FAIL';

const CREATE_TEST_ORDER = 'orders/CREATE_TEST_ORDER';
const CREATE_TEST_ORDER_SUCCESS = 'orders/CREATE_TEST_ORDER_SUCCESS';
const CREATE_TEST_ORDER_FAIL = 'orders/CREATE_TEST_ORDER_FAIL';

// Reducer
export default (state = null, action) => {
    switch (action.type) {
        case FETCH_ORDERS_SUCCESS:
            return action.result.data;
        case FETCH_ORDERS:
        case FETCH_ORDERS_FAIL:
        case CONFIRM_ORDER:
        case CONFIRM_ORDER_SUCCESS:
        case CONFIRM_ORDER_FAIL:
        case CREATE_TEST_ORDER:
        case CREATE_TEST_ORDER_SUCCESS:
        case CREATE_TEST_ORDER_FAIL:
            return null;
        default:
            return state;
    }
};

// Actions
export const fetchOrdersAction = () => ({
    types: [FETCH_ORDERS, FETCH_ORDERS_SUCCESS, FETCH_ORDERS_FAIL],
    promise: client => client.get('/api/orderItems')
});

export const confirmOrderItemAction = (orderItemId) => ({
    types: [CONFIRM_ORDER, CONFIRM_ORDER_SUCCESS, CONFIRM_ORDER_FAIL],
    promise: client => client.post(`/api/orderItems/${orderItemId}/confirm`),
    afterSuccess: (dispatch, getState, response) => {
        console.log('afterSuccess');
        dispatch(fetchOrdersAction());
    }
});

export const createTestOrderAction = () => ({
    types: [CREATE_TEST_ORDER, CREATE_TEST_ORDER_SUCCESS, CREATE_TEST_ORDER_FAIL],
    promise: client => client.post('/api/order/createtestorder'),
    afterSuccess: (dispatch, getState, response) => {
        console.log('afterSuccess createTestOrder');
        dispatch(fetchOrdersAction());
    }
});


