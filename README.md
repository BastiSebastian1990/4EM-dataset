# 4EM-dataset

# Model Repository Backend

## Overview

This project provides a **backend service for managing and storing domain models** in a structured and versioned repository.

The backend is implemented using **FastAPI** and interacts with a **GitHub repository** to store models, metadata, and documentation.

The system allows users to:

* Upload domain models
* Upload multiple types of model artifacts
* Version models automatically
* Store metadata about models
* Browse the repository structure
* Delete models and automatically update metadata

The repository structure is automatically maintained to ensure consistency.

---

# Architecture

The backend consists of three main components:

### API Layer

Implemented with **FastAPI**.

Responsible for:

* Handling HTTP requests
* Validating input data
* Receiving uploaded files
* Triggering repository operations

Main API modules:

* `upload.py`
* `delete.py`
* `browse.py`
* `update.py`

---

### Service Layer

Handles all interactions with the **GitHub repository**.

Main service:

```
app/services/github_service.py
```

Responsibilities:

* Upload files to GitHub
* Version models
* Maintain repository structure
* Update metadata
* Delete models and folders
* Browse repository contents

---

### Metadata Management

Metadata is stored in JSON files inside the repository.

Two metadata layers exist:

1. **Root metadata**
2. **Group metadata**

---

# Repository Structure

All models are stored inside the `Domain` directory.

```
Domain
 └ <domain>
    └ <model-group>
       ├ Gesamtmodell
       │   ├ v1
       │   ├ v2
       │   └ ...
       │
       ├ models
       │   ├ v1
       │   │   ├ concept_model.adl
       │   │   ├ rule_model.adl
       │   │   ├ process_model.adl
       │   │   └ subprocess_models
       │   │       ├ subprocess_1.adl
       │   │       └ subprocess_2.adl
       │   │
       │   └ v2
       │
       ├ description.txt
       └ metadata_<group>.json
```

---

# Model Types

The repository supports different model types.

Examples:

* Concept Model
* Actors Resource Model
* Rule Model
* Goal Model
* Product Service Model
* Technical Components Model
* Process Model

Each model type is optional.

---

# Model Versioning

The system automatically versions models.

Each upload creates a new version:

```
models
 ├ v1
 ├ v2
 └ v3
```

Version numbers are generated automatically using the next available version.

---

# Process Models

The repository supports **process hierarchies**.

Two types exist:

### Main Process Model

Stored together with other models in the version folder.

Example:

```
process_model.adl
```

### Subprocess Models

Stored in a dedicated subfolder:

```
subprocess_models/
```

Example:

```
models
 └ v1
    ├ process_model.adl
    └ subprocess_models
        ├ subprocess_payment.adl
        └ subprocess_shipping.adl
```

---

# Upload Workflow

The upload process follows these steps:

### 1. Request Validation

The API validates:

* required fields
* file formats
* decomposition constraints

---

### 2. Repository Structure Check

The system ensures the following structure exists:

```
Domain/
Domain/<domain>/
Domain/<domain>/<group>/
```

If directories are missing they are created automatically.

---

### 3. Model Upload

The following files may be uploaded:

* Gesamtmodell (`adl`)
* Description file (`txt`)
* Model artifacts

All files are stored in the correct repository structure.

---

### 4. Version Creation

A new version folder is created:

```
models/vX
```

All model artifacts are stored inside this folder.

---

### 5. Metadata Update

Two metadata files are updated:

### Group Metadata

```
Domain/<domain>/<group>/metadata_<group>.json
```

Contains information such as:

* Domain
* model group
* model name
* description
* model type
* model state
* author
* author type
* language
* validation status
* decomposition information
* additional information

---

### Root Metadata

```
metadata.json
```

Contains an overview of all domains and model groups in the repository.

---

# Delete Workflow

Models, groups, or entire domains can be deleted.

The deletion process performs the following steps:

1. Remove files and folders from the repository
2. Update `metadata.json`
3. Remove empty model groups
4. Remove empty domains

This guarantees that the metadata always reflects the actual repository structure.

---

# Repository Browsing

The API provides a browsing endpoint that returns the repository structure.

Example response:

```
{
  "domains": [
    {
      "domain": "Retail",
      "groups": [
        {
          "group": "OrderManagement",
          "models": [
            "concept_model.adl",
            "process_model.adl"
          ],
          "process_models": [
            "subprocess_payment.adl"
          ]
        }
      ]
    }
  ]
}
```

---

# Supported File Types

Currently supported model formats:

```
.adl
.txt
```

Additional formats can be added if required.

---

# Running the Backend

Start the FastAPI server using:

```
uvicorn main:app --reload
```

Swagger UI is available at:

```
http://127.0.0.1:8000/docs
```

---

# Running the Frontend

Start the REACT server using:

```
...\model_repo_backend\model-repository-frontend> npm run dev

```

REACT is available at:

```
http://localhost:5173/
http://localhost:5173/upload
http://localhost:5173/browse
```

---


# Future Improvements

Possible extensions include:

* authentication and access control
* model validation
* graphical repository browser
* integration with modeling tools
* automated model analysis
* frontend application for repository navigation

---

# License

This project is distributed under the terms of the repository license.
