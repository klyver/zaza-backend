import React, {Component} from 'react';
import {connect} from 'react-redux';
import {fetchOrdersAction, confirmOrderItemAction, createTestOrderAction} from '../../reducers/orders';

const OrdersPage = class extends Component {

    componentDidMount() {
        this.props.fetchOrdersAction();
    }

    render() {
        if (this.props.orders == null) {
            return <h1>Loading...</h1>
        }

        return (
            <div>
                <h1>Order Items</h1>
                <table>
                    <thead>
                        <tr>
                            <th>orderId</th>
                            <th>id</th>
                            <th>skuId</th>
                            <th>skuSourceId</th>
                            <th>quantity</th>
                            <th>status</th>
                            <th></th>
                        </tr>
                    </thead>
                    <tbody>
                    {this.props.orders.map((orderItem, index) => (
                        <tr
                            key={index}
                        >
                            <td>{orderItem.orderId}</td>
                            <td>{orderItem.id}</td>
                            <td>{orderItem.skuId}</td>
                            <td>{orderItem.skuSourceId}</td>
                            <td>{orderItem.quantity}</td>
                            <td>{orderItem.status}</td>
                            <td>
                                {this.props.manufacturer && orderItem.status === 'SUBMITTED_BY_CUSTOMER' ? <button onClick={ () => {
                                    this.props.confirmOrderItemAction(orderItem.id);
                                }}
                                >Confirm Order</button> : null}
                            </td>
                        </tr>
                    ))}
                    </tbody>
                </table>
                <div>
                    <button onClick={this.props.createTestOrderAction}>Create order (this is only for test purpose - should be removed later)</button>
                </div>
            </div>
        );
    }
};

export default connect(
    state => ({
        orders: state.orders,
        admin: state.authentication.admin,
        manufacturer: state.authentication.manufacturer
    }),
    {
        createTestOrderAction,
        fetchOrdersAction,
        confirmOrderItemAction
    }
)(OrdersPage);


