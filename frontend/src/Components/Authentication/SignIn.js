import React, { useState } from "react";
import Form from "react-bootstrap/Form";
import Button from "react-bootstrap/Button";
import { ToastContainer, toast } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";
import { NavLink, useNavigate } from "react-router-dom";
import axios from "axios";
import "./Authentication.css";
const SignIn = () => {
  const [inpval, setInpval] = useState({
    email: "",
    password: "",
  });
  const navigate = useNavigate();
  const getdata = (e) => {
    console.log(e.target.value);

    const { value, name } = e.target;
    console.log(value, name);

    setInpval(() => {
      return {
        ...inpval,
        [name]: value,
      };
    });
  };
  const [statusMessage, setStatusMessage] = useState({
    status: "",
    message: "",
  });
  const addData = (e) => {
    e.preventDefault();

    const { email, password } = inpval;
    if (email === "") {
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
      try {
        const formData = {
          email: email,
          password: password,
        };

        const res = axios
          .post("http://localhost:8080/api/user/login", formData)
          .then((response) => {
            // handle the response
            console.log(response.data.status);
            setStatusMessage({
              status: "success",
              message: response.data.message,
            });
            setTimeout(() => {
              setStatusMessage({
                status: "",
                message: "",
              });
            }, 2000);

            if (response.data.status === true) {
              navigate("/");
            } else {
              console.log("Cannot navigate");
            }
          })
          .catch((error) => {
            // handle errors
            console.log(error);
            setStatusMessage({
              status: "error",
              message: "Error while uploading file",
            });
          });
      } catch (error) {
        console.log(error);
      }
    }
  };

  return (
    <div className="vBase">
      <section>
        <div className="signInForm">
          <h3>Sign In</h3>
          <Form>
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
            Don't Have an Account{" "}
            <span>
              <NavLink to="/signup">Sign Up</NavLink>
            </span>{" "}
          </p>
        </div>
      </section>
      <ToastContainer />
    </div>
  );
};

export default SignIn;
