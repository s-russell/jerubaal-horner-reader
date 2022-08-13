import {useState} from "react";

const useReaderAPI = () =>{

    const getReading = async (number) => {
        const resp = await fetch(`api/reading/${number}`)
        const reading = await resp.json()
        return reading
    }

    return { getReading }
}

export default useReaderAPI