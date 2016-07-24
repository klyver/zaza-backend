import React, {Component} from 'react';
import {connect} from 'react-redux';
import {fetchProductOptionList} from '../../reducers/productOptionList';
import {createProduct} from '../../reducers/creatingProduct';
import {Link} from 'react-router';
import ProductEditor from '../components/ProductEditor';

const ProductCreateNewPage = class extends Component {

    constructor(props) {
        super(props);
    }

    componentDidMount() {
        this.props.fetchProductOptionList();
    }

    render() {
        this.state = {
            product: this.props.product
        };

        if (this.props.creatingProduct) {
            return <h1>Creating product...</h1>
        } else if (this.props.loading) {
            return <h1>Loading...</h1>
        }

        return (
            <div>
                <h2>Create new product</h2>
                <ProductEditor
                    product={this.state.product}
                    productOptions={this.props.productOptions}
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
                    this.props.createProduct(this.state.product);
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
            approved: false
        },
        loading: state.productOptionList.loading,
        productOptions: state.productOptionList.productOptions,
        admin: state.authentication.admin,
        creatingProduct: state.creatingProduct.loading,
        errorMessage: state.creatingProduct.errorMessage
    }),
    {
        fetchProductOptionList,
        createProduct
    }
)(ProductCreateNewPage);

