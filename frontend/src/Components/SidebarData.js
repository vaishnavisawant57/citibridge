import * as AiIcons from "react-icons/ai";
import * as BsIcons from "react-icons/bs";

export const SidebarData = [
  {
    title: "Upload File",
    path: "/",
    icons: <BsIcons.BsFileArrowUp />,
    cName: "nav-text",
  },
  {
    title: "Valid Transactions",
    path: "/valid",
    // icons: <GrIcons.GrValidate />,
    icons: <AiIcons.AiOutlineCheckCircle />,
    cName: "nav-text",
  },
  {
    title: "Invalid Transactions",
    path: "/invalid",
    icons: <AiIcons.AiOutlineStop />,
    cName: "nav-text",
  },
  {
    title: "Feed File",
    path: "/all",
    icons: <BsIcons.BsCheckAll />,
    cName: "nav-text",
  },
  {
    title: "File Status",
    path: "/filestatus",
    icons: <BsIcons.BsFileCheck />,
    cName: "nav-text",
  },
  {
    title: "Data Visualization",
    path: "/datavisualization",
    icons: <BsIcons.BsBarChartSteps />,
    cName: "nav-text",
  },
  {
    title: "Log Out",
    path: "/signin",
    icons: <AiIcons.AiOutlineLogout />,
    cName: "nav-text",
  },
];
