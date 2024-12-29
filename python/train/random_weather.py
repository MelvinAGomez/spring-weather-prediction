# Import necessary libraries
import pandas as pd
from sklearn.model_selection import train_test_split
from sklearn.ensemble import RandomForestClassifier
from sklearn.metrics import accuracy_score, classification_report
import warnings
import joblib
warnings.filterwarnings('ignore')

weather_df = pd.read_csv("./seattle-weather.csv")
weather_df.head()


# Select relevant features and target variable
X = weather_df[['precipitation', 'temp_max', 'temp_min', 'wind']]
y = weather_df['weather']


# Split the dataset into training and testing sets
X_train, X_test, y_train, y_test = train_test_split(X, y, test_size=0.2, random_state=42)

# Create a Random Forest Classifier
rf_classifier = RandomForestClassifier(n_estimators=100, random_state=42)

# Train the classifier
rf_classifier.fit(X_train, y_train)

# Make predictions on the test set
y_pred = rf_classifier.predict(X_test)

# Evaluate the model
accuracy = accuracy_score(y_test, y_pred)
classification_rep = classification_report(y_test, y_pred)

# Print the results
print("Accuracy: ",accuracy)
print("Classification Report",classification_rep)

joblib.dump(rf_classifier, "../weather_model.pkl")