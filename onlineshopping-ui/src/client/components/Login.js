import React,{ useState } from "react";
import { useSelector } from "react-redux";
import ValidateAction from "../../redux/action/ValidateAction";
import { Navigate } from 'react-router-dom';






const Login = () => {
    const [userDetails,setUserDetails] = useState({customerId:"",password:""});
    const validateAction = ValidateAction();
    const validate = useSelector((state)=>state.validate);
    const onInputChange =(event)=>{
        setUserDetails({...userDetails,[event.target.name]:event.target.value})
    };
    const onSubmit = (event) => {
        event.preventDefault();
        validateAction.login(userDetails);
      };

    return validate ? (
        <Navigate to="/" />
    ):(
        <React.Fragment>
      {/* <div className="text-center container align-items-center container"> */}
      <div className='container-sm'>
        <h1>Login Page</h1>
        <form >
          <div className="mb-3 row">
            <div className="input-group mb-3">
            <label className="col-sm-2 col-form-label" style={{width:"1px"}}>Username</label>
            <div class="col-sm-10">
            <input type="text" name="customerId" className="form-control"
              value={userDetails.customerId} onChange={onInputChange} required></input></div></div>
            <div className="input-group mb-3">
            <label className="col-sm-2 col-form-label">Password</label>
            <div class="col-sm-10">
            <input type="text" name="password" className="form-control"
              value={userDetails.password} onChange={onInputChange} required></input></div></div>
            <br></br>
          </div>
          <button className="btn btn-success" onClick={onSubmit}>
            Login
          </button>
          &emsp;&emsp;
          
        </form>
      </div>
    </React.Fragment>

    )

}
export default Login;