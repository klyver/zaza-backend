import {combineReducers} from 'redux';

const FETCH = 'productOptionList/FETCH';
const FETCH_SUCCESS = 'productOptionList/FETCH_SUCCESS';
const FETCH_FAIL = 'productOptionList/FETCH_FAIL';

// Reducer
const loading = (state = false, action) => {
    switch (action.type) {
        case FETCH:
            return true;
        case FETCH_SUCCESS:
            return false;
        case FETCH_FAIL:
            return false;
        default:
            return state;
    }
};
const productOptions = (state = [], action) => {
    switch (action.type) {
        case FETCH_SUCCESS:
            return action.result.data;
        default:
            return state;
    }
};

export default combineReducers({loading, productOptions});

// Actions
export const fetchProductOptionList = () => ({
    types: [FETCH, FETCH_SUCCESS, FETCH_FAIL],
    promise: client => client.get('/api/productOptions')
});

