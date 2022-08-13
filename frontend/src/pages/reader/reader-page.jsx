import {useParams} from "react-router-dom";
import useReaderAPI from "./api";
import {useEffect, useState} from "react";
import Reading from "./reading";

const ReaderPage = () => {
    const {number} = useParams("number")
    const {getReading} = useReaderAPI()
    const [ reading, setReading ] = useState([])

    useEffect( () => {
        const asyncWrapper = async () => {
            const nextReading = await getReading(number)
            setReading(nextReading)
        }
        asyncWrapper();
    }, [number])

    return <Reading reading={reading} />
}

export default ReaderPage