import React, {Component} from 'react';
import {connect} from 'react-redux';
import {fetchProductOptionsAction} from '../../reducers/productOptions';
import {fetchCategoriesAction} from '../../reducers/categories';
import {createProductAction} from '../../reducers/products';
import {Link} from 'react-router';
import ProductEditor from '../components/ProductEditor';

const ProductCreateNewPage = class extends Component {

    constructor(props) {
        super(props);
    }

    componentDidMount() {
        this.props.fetchProductOptionsAction();
        this.props.fetchCategoriesAction();
    }

    render() {
        this.state = {
            product: this.props.product
        };

        if (this.props.loading) {
            return <h1>Loading...</h1>
        }

        return (
            <div>
                <h2>Create new product</h2>
                <ProductEditor
                    product={this.state.product}
                    productOptions={this.props.productOptions}
                    categories={this.props.categories}
                    updateProduct={(product) => {
                        this.setState({
                            product: product
                        });
                    }}
                    admin={this.props.admin}
                    errorMessage={this.props.errorMessage}
                />
                <button onClick={ (e) => {
                    e.preventDefault();
                    this.props.createProductAction(this.state.product);
                }}
                >Create</button>
            </div>
        );
    }
};

export default connect(
    state => ({
        product: {
            sourceId: '',
            name: '',
            nameMandarin: '',
            description: '',
            descriptionMandarin: '',
            longDescription: '',
            longDescriptionMandarin: '',
            productOptions: [],
            skus: [],
            approved: false,
            categoryId: null,
            productAttributes: []
        },
        loading: state.products.loading || !state.categories || !state.productOptions,
        productOptions: state.productOptions,
        categories: state.categories,
        admin: state.authentication.admin,
        errorMessage: state.products.errorMessage
    }),
    {
        fetchCategoriesAction,
        fetchProductOptionsAction,
        createProductAction
    }
)(ProductCreateNewPage);

