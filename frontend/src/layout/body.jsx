import {BrowserRouter, Route, Routes, Navigate} from "react-router-dom";
import ReaderPage from "../pages/reader/reader-page";


const Body = () => <>
<BrowserRouter>
    <Routes>
       <Route path="*" element={<Navigate to="/1" replace />}/>
        <Route path={"/:number"} element={<ReaderPage />} />
    </Routes>
</BrowserRouter>
</>

export default Body