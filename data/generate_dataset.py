import pandas as pd
import random
random.seed(42)

DOMAINS={
    "AI/ML":[
        "Python, TensorFlow, Pandas",
        "Python, PyTorch, NumPy",
        "Python, Scikit-learn, Flask",
        "Python, BERT, HuggingFace",
        "Python, OpenCV, YOLO",
        "Python, Keras, Matplotlib"
    ],
    "Web Dev":[
       "React, Node.js, MongoDB",
        "React, Express, MySQL",
        "Vue.js, Django, MySQL",
        "Next.js, Tailwind, Firebase",
        "Angular, Spring Boot, MySQL" 
    ],
    "IoT":[
        "Arduino, Python, MQTT",
        "Raspberry Pi, Python, Firebase",
        "Arduino, C++, Bluetooth",
        "ESP32, MicroPython, MQTT",
        "Raspberry Pi, OpenCV, Python"
    ],
    "Cybersecurity":[
        "Python, Wireshark, Metasploit",
        "Python, Nmap, Kali Linux",
        "Python, Scapy, Burp Suite",
        "Python, Snort, Wireshark"
    ],
    "Mobile":[
        "Flutter, Dart, Firebase",
        "React Native, Node.js, MongoDB",
        "Android, Java, SQLite",
        "Swift, iOS, CoreData"
    ],
    "Blockchain":[
        "Solidity, Ethereum, React",
        "Solidity, Hardhat, Web3.js",
        "Python, Web3.py, Ethereum"
    ],
    "Cloud":[
        "AWS, Python, Docker",
        "Azure, Kubernetes, Terraform",
        "GCP, Python, Pub/Sub"
    ],
    "AR/VR":[
        "Unity, C#, ARCore",
        "Unreal Engine, C++, VR",
        "Unity, Vuforia, C#"
    ]
}

TITLES={
    "AI/ML":[
                "Crop Disease Detection using CNN",
        "Student Performance Prediction System",
        "Fake News Detection using NLP",
        "Sentiment Analysis of Reviews",
        "Real-time Object Detection System",
        "AI Chatbot using NLP",
        "Medical Image Classification",
        "Stock Price Prediction Model",
        "Resume Screening using AI",
        "Road Sign Recognition System",
        "Face Recognition Attendance System",
        "Hand Gesture Recognition",
        "Spam Email Classifier",
        "Brain Tumor Detection using MRI",
        "Sign Language Translator",
        "Drowsiness Detection System",
        "Air Quality Prediction Model",
        "Customer Churn Prediction",
        "Fraud Detection System",
        "Loan Default Prediction"

    ],
    "Web Dev": [
        "E-Commerce Platform with Recommendations",
        "College ERP System",
        "Online Examination Portal",
        "Job Portal with AI Matching",
        "Social Media Dashboard",
        "Library Management System",
        "Hospital Appointment System",
        "Placement Management Portal",
        "Online Food Ordering System",
        "Event Management Platform",
        "Alumni Connect Portal",
        "Student Grievance System",
        "Online Voting System",
        "College Fest Website",
        "Blood Bank Management System"
    ],
    "IoT": [
        "Smart Home Automation System",
        "Precision Agriculture Monitoring",
        "Smart Parking System",
        "Health Monitoring Wearable",
        "Smart Irrigation System",
        "Industrial Equipment Monitor",
        "Smart Energy Meter",
        "Waste Management System",
        "Smart Street Light Control",
        "Vehicle Tracking System"
    ],
    "Cybersecurity": [
        "Network Intrusion Detection System",
        "Password Strength Analyzer",
        "Phishing Website Detector",
        "Vulnerability Scanner Tool",
        "Malware Analysis System",
        "Firewall Rule Optimizer",
        "Steganography Tool",
        "Digital Forensics Framework"
    ],
    "Mobile": [
        "Campus Navigation App",
        "Expense Tracker Application",
        "Food Delivery Clone App",
        "Fitness Tracker Application",
        "Event Management Mobile App",
        "E-Learning Mobile Platform",
        "Ride Sharing Application",
        "Mental Health Support App"
    ],
    "Blockchain": [
        "Decentralized Voting System",
        "NFT Marketplace Platform",
        "Supply Chain on Blockchain",
        "Smart Contract Audit Tool",
        "Decentralized Finance App",
        "Certificate Verification System"
    ],
    "Cloud": [
        "Serverless REST API System",
        "Auto-scaling Web Application",
        "Cloud Cost Optimizer Tool",
        "Multi-cloud Storage Manager",
        "Microservices Architecture Demo"
    ],
    "AR/VR": [
        "AR Campus Tour Application",
        "VR Classroom Simulator",
        "AR Product Visualizer",
        "Virtual Lab Simulation",
        "AR Navigation System"
    ],
}

MENTORS=[
    "Dr.K.T.Mane", "Prof. Radhika Dhanal", "Dr. Kapil Kadam",
    "Prof. Sunny Mohite", "Dr. Pardesi", "Dr. Khan",
    "Prof. Jitkar", "Dr. Joshi", "Prof. Verma", "Dr. Nair"
]

DISTRIBUTION = {
    2019: {"AI/ML":18,"Web Dev":18,"IoT":4,"Cybersecurity":4,"Mobile":5,"Blockchain":1,"Cloud":1,"AR/VR":1},
    2020: {"AI/ML":24,"Web Dev":20,"IoT":6,"Cybersecurity":5,"Mobile":4,"Blockchain":2,"Cloud":1,"AR/VR":1},
    2021: {"AI/ML":35,"Web Dev":22,"IoT":8,"Cybersecurity":6,"Mobile":5,"Blockchain":4,"Cloud":2,"AR/VR":1},
    2022: {"AI/ML":52,"Web Dev":22,"IoT":14,"Cybersecurity":10,"Mobile":7,"Blockchain":5,"Cloud":3,"AR/VR":2},
    2023: {"AI/ML":88,"Web Dev":20,"IoT":28,"Cybersecurity":30,"Mobile":9,"Blockchain":5,"Cloud":4,"AR/VR":3},
    2024: {"AI/ML":112,"Web Dev":18,"IoT":38,"Cybersecurity":18,"Mobile":8,"Blockchain":3,"Cloud":6,"AR/VR":2},
}

# Completion probability per year
COMPLETION = {
    2019: 0.95,
    2020: 0.92,
    2021: 0.88,
    2022: 0.85,
    2023: 0.89,
    2024: 0.65
}

rows=[]
used_titles=set()

for year,dist in DISTRIBUTION.items():
    for domain, count in dist.items():
        for i in range(count):

            #pick unique title
            available=TITLES.get(domain,[f'{domain} Project'])
            title_base = random.choice(available)
            title = title_base if title_base not in used_titles else f"{title_base} {year}"
            used_titles.add(title)

            # Pick tech stack
            tech = random.choice(DOMAINS.get(domain, ["Python"]))

            # Assign status
            prob = COMPLETION[year]
            rand = random.random()
            if rand < prob:
                status = "Completed"
            elif rand < prob + 0.07:
                status = "Ongoing"
            else:
                status = "Abandoned"

            # Team size (weighted — teams of 3 most common)
            team = random.choices(
                [1, 2, 3, 4, 5],
                weights=[5, 30, 40, 15, 10]
            )[0]

            mentor = random.choice(MENTORS)

            rows.append({
                "project_title": title,
                "domain":        domain,
                "tech_stack":    tech,
                "batch_year":    year,
                "status":        status,
                "team_size":     team,
                "mentor":        mentor
            })

df = pd.DataFrame(rows)
df.to_csv("cse_projects.csv", index=False)

print(f"✅ Dataset generated: {len(df)} records")
print(f"\nDomain breakdown:")
print(df["domain"].value_counts())
print(f"\nYear breakdown:")
print(df["batch_year"].value_counts().sort_index())