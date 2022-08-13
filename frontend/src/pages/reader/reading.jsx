const Reading = ({reading}) => {
    return <>
        <ul>
            {reading.map(({book, chapter}) => <li key={`${book}-${chapter}`}>{book} {chapter}</li>)}
        </ul>
    </>
}

export default Reading