import React from 'react';
import {redirectToLoginWithMessage} from '../reducers/authentication';
import {connect} from 'react-redux';

const mapStateToProps = (state) => ({
    loading: state.authentication.loading,
    roles: state.authentication.roles

});
const mapDispatchToProps = {
    redirectToLoginWithMessage
};

const privateRoute = (allowedRole, Wrapped) => connect(mapStateToProps, mapDispatchToProps)(class extends React.Component {

    componentDidMount() {
        this.redirectIfNotLogged(this.props);
    }

    componentWillReceiveProps(nextProps) {
        this.redirectIfNotLogged(nextProps);
    }

    redirectIfNotLogged(props) {
        const {loading, roles} = props;
        const allowed = roles.indexOf(allowedRole) > -1;
        if (loading === false && !allowed) {
            this.props.redirectToLoginWithMessage('LOGIN_ERROR_PRIVATE');
        }
    }

    render() {
        const {loading, roles} = this.props;
        const allowed = roles.indexOf(allowedRole) > -1;
        if (loading || !allowed) {
            return (
                <div>
                    <div>Loading...</div>
                </div>
            );
        }

        return <Wrapped {...this.props} />;
    }
});

export default privateRoute;
