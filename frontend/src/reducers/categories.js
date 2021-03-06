const FETCH = 'categories/FETCH';
const FETCH_SUCCESS = 'categories/FETCH_SUCCESS';
const FETCH_FAIL = 'categories/FETCH_FAIL';

// Reducer
export default (state = null, action) => {
    switch (action.type) {
        case FETCH_SUCCESS:
            return action.result.data;
        case FETCH:
        case FETCH_FAIL:
            return null;
        default:
            return state;
    }
};

// Actions
export const fetchCategoriesAction = () => ({
    types: [FETCH, FETCH_SUCCESS, FETCH_FAIL],
    promise: client => client.get('/api/categories')
});

