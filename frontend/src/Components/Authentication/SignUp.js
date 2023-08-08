import React, { useState } from "react";
import Form from "react-bootstrap/Form";
import Button from "react-bootstrap/Button";
import { NavLink, useNavigate } from "react-router-dom";
import { ToastContainer, toast } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";
import axios from "axios";
import "./Authentication.css";
const SignUp = () => {
  const [inpval, setInpval] = useState({
    name: "",
    email: "",
    password: "",
  });
  const navigate = useNavigate();

  const getdata = (e) => {
    // console.log(e.target.value);

    const { value, name } = e.target;
    // console.log(value, name);

    setInpval(() => {
      return {
        ...inpval,
        [name]: value,
      };
    });
  };

  const addData = async (e) => {
    e.preventDefault();
    const { name, email, password } = inpval;

    if (name === "") {
      toast.error(" name field is requred!", {
        position: "top-center",
      });
    } else if (email === "") {
      toast.error("email field is requred", {
        position: "top-center",
      });
    } else if (!email.includes("@")) {
      toast.error("plz enter valid email addres", {
        position: "top-center",
      });
    } else if (password === "") {
      toast.error("password field is requred", {
        position: "top-center",
      });
    } else if (password.length < 5) {
      toast.error("password length greater five", {
        position: "top-center",
      });
    } else {
      console.log("data added succesfully");
      try {
        const formData = {
          name: name,
          email: email,
          password: password,
        };

        const response = await axios.post(
          "http://localhost:8080/api/user/save",
          formData
        );
        navigate("/");
      } catch (error) {
        console.log(error);
      }
    }
  };

  return (
    <div className="vBase">
      <section>
        <div className="signInForm">
          <h3>Sign Up</h3>
          <Form>
            <Form.Group controlId="formBasicEmail">
              <Form.Control
                type="text"
                name="name"
                onChange={getdata}
                placeholder="Enter Your Name"
              />
            </Form.Group>
            <Form.Group controlId="formBasicEmail">
              <Form.Control
                type="email"
                name="email"
                onChange={getdata}
                placeholder="Enter email"
              />
            </Form.Group>

            <Form.Group controlId="formBasicPassword">
              <Form.Control
                type="password"
                name="password"
                onChange={getdata}
                placeholder="Password"
              />
            </Form.Group>
            <Button
              variant="primary"
              className="button-10"
              onClick={addData}
              type="submit"
            >
              Submit
            </Button>
          </Form>
          <p className="signInLink">
            Already Have an Account{" "}
            <span>
              <NavLink to="/signin">Sign In</NavLink>
            </span>{" "}
          </p>
        </div>
      </section>
      <ToastContainer />
    </div>
  );
};

export default SignUp;
