

// Function to get a value from localStorage
export const getFromLocalStorage = (
    key: string
) => {
    const storedValue = localStorage.getItem(key);

    // Check if the storedValue is null before parsing
    if (storedValue) {
        try {
            return JSON.parse(storedValue);
        } catch (error) {
            console.error('Error parsing JSON:', error);
            return null;
        }
    }
    return null;
}