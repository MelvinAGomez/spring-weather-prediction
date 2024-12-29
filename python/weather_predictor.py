import sys
import warnings
import pandas as pd
from sklearn.ensemble import RandomForestClassifier
from sklearn.exceptions import DataConversionWarning

# Suppress warnings
warnings.filterwarnings(action='ignore', category=DataConversionWarning)

# Load the model (Assuming it is already saved)
from joblib import load
model = load("python/weather_model.pkl")

# Read input from command-line arguments
try:
    precipitation = float(sys.argv[1])
    temp_max = float(sys.argv[2])
    temp_min = float(sys.argv[3])
    wind = float(sys.argv[4])

    # Prepare the input data
    input_data = pd.DataFrame([[precipitation, temp_max, temp_min, wind]],
                              columns=['precipitation', 'temp_max', 'temp_min', 'wind'])

    # Make a prediction
    prediction = model.predict(input_data)

    # Print only the prediction
    print(prediction[0])

except Exception as e:
    print(f"Error: {e}")
    sys.exit(1)
