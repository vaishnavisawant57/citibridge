import React from "react";
import { createRoot } from "react-dom/client";
import {
  createBrowserRouter,
  RouterProvider,
  Route,
  Outlet,
  createRoutesFromElements,
} from "react-router-dom";
import Valid from "./Routes/Valid";
import Home from "./Routes/Home";
import Invalid from "./Routes/Invalid";
import All from "./Routes/All";
import FileStatus from "./Routes/FileStatus";
import DataVisualization from "./Routes/DataVisualization";
import SignIn from "./Components/Authentication/SignIn";
import SignUp from "./Components/Authentication/SignUp";
import "./App.css";

const AppLayout = () => (
  <>
    <Outlet />
  </>
);

const router = createBrowserRouter(
  createRoutesFromElements(
    <Route element={<AppLayout />}>
      <Route path="/" element={<Home />} />
      <Route path="/valid" element={<Valid />} />
      <Route path="/invalid" element={<Invalid />} />
      <Route path="/all" element={<All />} />
      <Route path="/filestatus" element={<FileStatus />} />
      <Route path="/datavisualization" element={<DataVisualization />} />
      <Route path="/signin" element={<SignIn />} />
      <Route path="/signup" element={<SignUp />} />
    </Route>
  )
);

/*const router = createBrowserRouter([
  {
    element: <AppLayout />,
    children :[
  
  {
    path: "/",
    element: <Home />,
  },
  {
    path: "valid",
    element: <Valid />,
  },
  {
    path: "invalid",
    element: <Invalid />,
  },
  {
    path: "all",
    element: <All />,
  },
  {
    path: "filestatus",
    element: <FileStatus />,
  },
  {
    path: "datavisualization",
    element: <DataVisualization />,
  },
  {
    path: "signin",
    element: <SignIn />,
  },
  {
    path: "signup",
    element: <SignUp />,
  },
]
  }

]);*/

createRoot(document.getElementById("root")).render(
  <RouterProvider router={router} />
);
