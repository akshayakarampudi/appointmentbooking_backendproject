from fastapi import FastAPI
from fastapi.middleware.cors import CORSMiddleware

from app.controllers.auth_controller import router as authRouter
from app.controllers.user_controller import router as userRouter
from app.controllers.doctor_controller import router as doctorRouter
from app.controllers.appointment_controller import router as appointmentRouter

app = FastAPI(
    title="Appointment Gateway"
)

app.add_middleware(
    CORSMiddleware,
    allow_origins=["http://localhost:5173"],
    allow_credentials=True,
    allow_methods=["*"],
    allow_headers=["*"]
)

app.include_router(authRouter)
app.include_router(userRouter)
app.include_router(doctorRouter)
app.include_router(appointmentRouter)

@app.get("/")
def home():
    return {
        "message": "Gateway Running"
    }