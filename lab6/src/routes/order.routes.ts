import { Router } from "express";
import orderController from "../controllers/order.controller";

const router = Router();

router.post("/", (req, res) => orderController.create(req, res));
router.get("/", (req, res) => orderController.getAll(req, res));
router.get("/:id", (req, res) => orderController.getById(req, res));

export default router;
