import * as React from 'react';
import { request, setAuthHeader } from '../../axios_helper';
import WelcomeContent from '../WelcomeContent';
import Form from './Form';
import PricesData from '../data/PricesData';

class LoginForm extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            componentToShow: "form",
            active: "login",
            login: "",
            password: "",
            passwordRep: "",
            error: "",
            success: ""
        };
    }


    onChangeHandler = (event) => {
        let name = event.target.name;
        let value = event.target.value;
        this.setState({ [name]: value });
    }

    onSubmitLogin = (e) => {
        e.preventDefault();
        request(
            "POST",
            "/api/v1/auth/login",
            {
                login: this.state.login,
                password: this.state.password
            }).then(
                (response) => {
                    localStorage.setItem('token', response.data.token);
                    setAuthHeader(localStorage.getItem('token'));
                    this.props.onLogin();
                    this.setState({ success: 'Logowanie udane' });
                    this.setState({ error: '' });
                    this.setState({ componentToShow: "welcome" });
                }).catch(
                    (error) => {
                        setAuthHeader(null);
                        this.setState({ error: 'Niepoprawne dane logowania' });
                        this.setState({ success: '' });

                    }
                );
    }

    onSubmitRegister = (e) => {
        e.preventDefault();
        if (this.state.password !== this.state.passwordRep) {
            this.setState({ error: 'Hasła nie są identyczne' });
        }
        else {
            request(
                "POST",
                "/api/v1/auth/register",
                {
                    login: this.state.login,
                    password: this.state.password
                }).then(
                    (response) => {
                        localStorage.setItem('token', response.data.token);
                        setAuthHeader(localStorage.getItem('token'));
                        this.setState({ success: 'Rejestracja udana' });
                        this.setState({ error: '' });
                    }).catch(
                        (error) => {
                            setAuthHeader(null);
                            this.setState({ error: 'Niepoprawne dane rejestracji' });
                            this.setState({ success: '' });
                        }
                    );
        }

    }

    render() {
        const isLoginActive = this.state.active === "login";
        const submitButtonText = isLoginActive ? "Zaloguj się" : "Zarejestruj się";

        return (
            <>
                <div className="row justify-content-center">
                    <div className="col-4">
                        {this.state.error && <div className="alert alert-danger">{this.state.error}</div>}
                        {this.state.success && <div className="alert alert-success">{this.state.success}</div>}
                    </div >
                </div >
                {
                    this.state.componentToShow === "form" &&
                    <Form
                        active={this.state.active}
                        onChange={(value) => this.setState({ active: value })}
                        onSubmit={this.state.active === 'login' ? this.onSubmitLogin : this.onSubmitRegister}
                        onChangeHandler={this.onChangeHandler}
                    />
                }
                {this.state.componentToShow === "welcome" && <WelcomeContent />}
            </>
        );
    }
}
export default LoginForm;
