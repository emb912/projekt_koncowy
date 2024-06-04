import * as React from 'react';
import classNames from 'classnames';
import 'bootstrap/dist/css/bootstrap.min.css'
import '../../styles/Buttons.css';
export default class Form extends React.Component {

    render() {
        const isLoginActive = this.props.active === "login";
        const submitButtonText = isLoginActive ? "Zaloguj się" : "Zarejestruj się";

        return (
            <div className="row justify-content-center">
                <div className="col-4">
                    <ul className="nav nav-pills nav-justified mb-3" id="ex1" role="tablist">
                        <li className="nav-item" role="presentation">
                            <button className={classNames("nav-link", isLoginActive ? "blue-btn" : "blue-text")} id="tab-login"
                                onClick={() => this.props.onChange("login")}>Logowanie</button>
                        </li>
                        <li className="nav-item" role="presentation">
                            <button className={classNames("nav-link", !isLoginActive ? "blue-btn" : "blue-text")} id="tab-register"
                                onClick={() => this.props.onChange("register")}>Rejestracja</button>
                        </li>
                    </ul>

                    <div className="tab-content">
                        <div className={classNames("tab-pane", "fade", isLoginActive ? "show active" : "")} id="pills-login" >
                            <form onSubmit={this.props.onSubmit}>
                                <div className="form-outline mb-4">
                                    <input type="login" id="loginName" name="login" className="form-control" onChange={this.props.onChangeHandler} required />
                                    <label className="form-label" htmlFor="loginName">Login</label>
                                </div>

                                <div className="form-outline mb-4">
                                    <input type="password" id="loginPassword" name="password" className="form-control" onChange={this.props.onChangeHandler} required />
                                    <label className="form-label" htmlFor="loginPassword">Hasło</label>
                                </div>
                                <div className="col-md-12 text-center" style={{ marginTop: '30px' }}>
                                    <button type="submit" className="btn blue-btn btn-block mb-4 " >{submitButtonText}</button>
                                </div>
                            </form>
                        </div>
                        <div className={classNames("tab-pane", "fade", !isLoginActive ? "show active" : "")} id="pills-register" >
                            <form onSubmit={this.props.onSubmit}>
                                <div className="form-outline mb-4">
                                    <input type="text" id="login" name="login" className="form-control" onChange={this.props.onChangeHandler} required />
                                    <label className="form-label" htmlFor="login">Login</label>
                                </div>

                                <div className="form-outline mb-4">
                                    <input type="password" id="registerPassword" name="password" className="form-control" onChange={this.props.onChangeHandler} required />
                                    <label className="form-label" htmlFor="registerPassword">Hasło</label>
                                </div>

                                <div className="form-outline mb-4">
                                    <input type="password" id="registerPasswordRep" name="passwordRep" className="form-control" onChange={this.props.onChangeHandler} required />
                                    <label className="form-label" htmlFor="registerPassword">Powtórz hasło</label>
                                </div>

                                <div className="col-md-12 text-center" style={{ marginTop: '30px' }}>
                                    <button type="submit" className="btn blue-btn btn-block mb-3 ">{submitButtonText}</button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        );
    }
}
