import React, { useState } from "react";
import { SidebarData } from "../SidebarData";
import "../NavBar/Nav.css";
import { IconContext } from "react-icons";
import { Link, useLocation } from "react-router-dom";

function Navbar() {
  const [sidebar, setSidebar] = useState(false);
  const location = useLocation(); // Get the current location

  const showSidebar = () => setSidebar(!sidebar);

  return (
    <>
      <IconContext.Provider value={{ color: "undefined" }}>
        <nav className={sidebar ? "nav-menu active" : "nav-menu"}>
          <ul
            className={`nav-menu-items ${
              sidebar ? "nav-menu-items-active" : "nav-menu-items-inactive"
            }`}
            onClick={showSidebar}
          >
            {SidebarData.map((item, index) => {
              return (
                <Link
                  key={index}
                  className={`${item.cName} ${
                    item.path === location.pathname ? "active" : ""
                  }`}
                  to={item.path}
                >
                  {item.icons}
                  <span>{item.title}</span>
                </Link>
              );
            })}
          </ul>
        </nav>
      </IconContext.Provider>
    </>
  );
}

export default Navbar;
