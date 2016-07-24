import React, {Component} from 'react';
import {connect} from 'react-redux';
import {login} from '../../reducers/authentication';

const errorMessages = {
    LOGIN_ERROR_PRIVATE: 'Please login to access this page',
    LOGIN_ERROR_UNAUTHORIZED: 'Please login to access this resource',
    LOGIN_ERROR_BADLOGIN: 'Incorrect user and password'
};

class LoginForm extends Component {

    constructor(props) {
        super(props);
        this.state = {
            username: '',
            password: ''
        };
    }

    render() {
        return (
            <div>
                <h2>Login Page</h2>

                {this.props.errorKey ? <p>{errorMessages[this.props.errorKey]}</p> : null}

                <form>
                    <div>
                        <span>Login</span>
                        <TextInput
                            value={this.state.username}
                            onChange={(newValue) => {
                                this.setState({username: newValue});
                            }}
                        />
                    </div>
                    <div>
                        <span>Password</span>
                        <TextInput
                            value={this.state.password}
                            onChange={(newValue) => {
                                this.setState({password: newValue});
                            }}
                        />
                    </div>
                    <button type="submit" onClick={ (e) => {
                        e.preventDefault();
                        this.props.login(this.state.username, this.state.password);
                    }}
                    >
                        Login
                    </button>
                </form>

            </div>
        );
    }
}

const TextInput = ({value, onChange}) => (
    <input
        type="text"
        value={value}
        onChange={(e) => {
            onChange(e.target.value);
        }}
    />
);

export default connect(
    state => ({errorKey: state.authentication.errorMessage}),
    {login}
)(LoginForm);
